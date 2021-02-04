package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/** This is a bad approach.
 *  如果在找到了循环之后，使用那两个点分别作为起点终点在标记过的点中进行DFS
 *  并标识连线会更好
 *  可以避免本题会出现的以下问题
 *  如果有一条或多条DFS到头的路径，而循环位于零一分叉，可以避免多余的线。
 */

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    int s = maze.xyTo1D(1, 1);
    boolean cycleFound = false;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        marked[s] = true;
        announce();
        CycleFinder(s, s);
        announce();
    }

    // Helper methods go here
    private void CycleFinder(int v, int from) {
        for (int w : maze.adj(v)) {
            if (cycleFound) {
                return;
            }
            if (marked[w] && w != from) {
                cycleFound = true;
                if (w != s) {
                    clearedge(edgeTo[w]);
                }
                edgeTo[w] = v;
                return;
            } else if(!marked[w]){
                marked[w] = true;
                edgeTo[w] = v;
                announce();
                CycleFinder(w, v);
                announce();
            }
        }
    }

    private void clearedge(int v) {
        int w = edgeTo[v];
        while(v != s) {
            edgeTo[v] = Integer.MAX_VALUE;
            v = w;
            w = edgeTo[v];
        }
    }

}

