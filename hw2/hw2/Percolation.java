package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int N;
    private final int[][] grid;
    private int numbers;

    private WeightedQuickUnionUF helper;

    private int xyTo1D(int row, int col){
        if (row > N - 1 || col > N - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("xyTo1D：index out of range!");
        }
        return row * N + col;
    }

    private void unionhelper(int row, int col) {
        if (row > 0) {
            if (isOpen(row-1, col)) {
                helper.union(xyTo1D(row-1, col), xyTo1D(row, col));
            }
        }
        if (row < N-1) {
            if (isOpen(row+1, col)) {
                helper.union(xyTo1D(row+1, col), xyTo1D(row, col));
            }
        }
        if (col < N-1) {
            if (isOpen(row, col+1)) {
                helper.union(xyTo1D(row, col+1), xyTo1D(row, col));
            }
        }
        if (col > 0) {
            if (isOpen(row, col-1)) {
                helper.union(xyTo1D(row, col-1), xyTo1D(row, col));
            }
        }
    }

    public Percolation(int N){
        if (N < 0) {
            throw new java.lang.IndexOutOfBoundsException("N should be bigger than 0!");
        }
        this.N = N;
        this.numbers = 0;
        this.helper = new WeightedQuickUnionUF(N * N);
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
        unionhelper(row, col);
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
        for (int i = 0; i < N; i++) {
            if (helper.connected(xyTo1D(row, col), xyTo1D(0, i))) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numbers;
    }

    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (isFull(N-1, i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        test.open(3, 4);
        test.open(2, 4);
        System.out.println(test.helper.connected(test.xyTo1D(3,4), test.xyTo1D(2,4) ));
        test.open(2, 2);
        System.out.println(test.helper.connected(test.xyTo1D(2,2), test.xyTo1D(2,4) ));
        test.open(2, 3);
        System.out.println(test.helper.connected(test.xyTo1D(2,2), test.xyTo1D(2,4) ));
        test.open(0, 2);
        System.out.println(test.isFull(2, 2));
        test.open(1, 2);
        System.out.println(test.isFull(2, 2));
    }
}
