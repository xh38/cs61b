package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {

    private int r = 34;
    private int g = 0;
    private int b = 231;

    public Clorus(double e) {
        super("clorous");
        energy = e;
    }
    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy = energy * 0.5;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empty = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");

        if (empty.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plips.size() > 0) {
            Direction movdir = Direction.LEFT;
            if (plips.size() == 1) {
                movdir = plips.get(0);
            } else {
                movdir = HugLifeUtils.randomEntry(plips);
            }
            return new Action(Action.ActionType.ATTACK, movdir);
        } else if (energy > 1) {
            Direction movdir = Direction.LEFT;
            if (empty.size() == 1) {
                movdir = empty.get(0);
            } else {
                movdir = HugLifeUtils.randomEntry(empty);
            }
            return new Action(Action.ActionType.REPLICATE, movdir);
        } else {
            Direction movdir = Direction.LEFT;
            if (empty.size() == 1) {
                movdir = empty.get(0);
            } else {
                movdir = HugLifeUtils.randomEntry(empty);
            }
            return new Action(Action.ActionType.MOVE, movdir);
        }
    }

    @Override
    public Color color() {
        return new Color(r, g, b);
    }
}
