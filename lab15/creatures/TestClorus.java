package creatures;

import huglife.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.stay();
        assertEquals(1.96, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus cchild = c.replicate();
        assertEquals(c.energy(), cchild.energy(), 0.01);
        assertNotSame(c, cchild);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(actual, expected);

        surrounded.put(Direction.TOP, new Plip(2));
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(actual, expected);

        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(actual, expected);

        c = new Clorus(0.8);
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(actual, expected);
    }
}
