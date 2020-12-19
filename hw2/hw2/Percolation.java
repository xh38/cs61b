package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int N;
    private final int[][] grid;
    private int numbers;

    private int start;
    private int end;

    private WeightedQuickUnionUF helper;
    private WeightedQuickUnionUF isFullhelper;

    private int xyTo1D(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("xyTo1D：index out of range!");
        }
        return row * N + col;
    }

    private void unionhelper(int row, int col, WeightedQuickUnionUF help) {
        if (row > 0) {
            if (isOpen(row - 1, col)) {
                help.union(xyTo1D(row - 1, col), xyTo1D(row, col));
            }
        }
        if (row < N - 1) {
            if (isOpen(row + 1, col)) {
                help.union(xyTo1D(row + 1, col), xyTo1D(row, col));
            }
        }
        if (col < N - 1) {
            if (isOpen(row, col + 1)) {
                help.union(xyTo1D(row, col + 1), xyTo1D(row, col));
            }
        }
        if (col > 0) {
            if (isOpen(row, col - 1)) {
                help.union(xyTo1D(row, col - 1), xyTo1D(row, col));
            }
        }
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IndexOutOfBoundsException("N should be bigger than 0!");
        }
        this.N = N;
        this.numbers = 0;
        this.helper = new WeightedQuickUnionUF(N * N + 2);
        this.isFullhelper = new WeightedQuickUnionUF(N * N + 1);
        this.start = N * N;
        this.end = start + 1;
        for (int i = 0; i < N; i++) {
            helper.union(start, xyTo1D(0, i));
            isFullhelper.union(start, xyTo1D(0, i));
            helper.union(end, xyTo1D(N - 1, i));
        }
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void open(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("Open: index out of range!");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = 1;
        unionhelper(row, col, helper);
        unionhelper(row, col, isFullhelper);
        numbers += 1;
    }

    public boolean isOpen(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("isOpen: index out of range!");
        }
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("isFull: index out of range!");
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return isFullhelper.connected(xyTo1D(row, col), start);
    }

    public int numberOfOpenSites() {
        return numbers;
    }

    public boolean percolates() {
        return helper.connected(start, end);
    }

    public static void main(String[] args) {
        Percolation test = new Percolation(-10);
        test.open(3, 4);
        test.open(2, 4);
        System.out.println(test.helper.connected(test.xyTo1D(3, 4), test.xyTo1D(2, 4)));
        test.open(2, 2);
        System.out.println(test.helper.connected(test.xyTo1D(2, 2), test.xyTo1D(2, 4)));
        test.open(2, 3);
        System.out.println(test.helper.connected(test.xyTo1D(2, 2), test.xyTo1D(2, 4)));
        test.open(0, 2);
        System.out.println(test.isFull(2, 2));
        test.open(1, 2);
        System.out.println(test.isFull(2, 2));
    }
}
