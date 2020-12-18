package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int[][] grid;
    public Percolation(int N){
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void open(int row, int col) {
        if (row > N - 1 || col > N - 1) {
            throw new java.lang.IndexOutOfBoundsException("row or col is too large!");
        }
        grid[row][col] = 1;

    }

    public boolean isOpen(int row, int col) {
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        return false;
    }

    public int numberOfOpenSites() {
        return 0;
    }

    public boolean percolates() {
        return false;
    }

    public static void main(String[] args) {

    }
}
