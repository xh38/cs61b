package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    public void addHexagon(TETile[][] world, int size, int x, int y, TETile t){
        int width;
        int p_x;
        for (int i = 0; i < 2 * size; i++) {
            p_x = getstartx(x, i, size);
            width = getwidth(size, i);
            addrow(world, p_x, y+i, width,t);
        }
    }

    private int getstartx(int x, int i, int size) {
        int index;
        if (i < size) {
            index = size - i - 1;
        } else {
            index = i - size;
        }
        return x + index;
    }

    private int getwidth(int size, int i) {
        int width = size;
        if (i < size) {
            width += i * 2;
        } else {
            width += ((2 * size - 1) - i) * 2;
        }
        return width;
    }

    private void addrow(TETile[][] world, int x, int y, int width, TETile t){
        for (int i = x; i < x + width; i++) {
            world[i][y] = t;
        }
    }

    public TETile[][] buildHexWorld(){

    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        int WIDTH = 60;
        int HEIGHT = 40;
        HexWorld HEX = new HexWorld();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);



        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        HEX.addHexagon(world, 3, 20,20, Tileset.WALL);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
