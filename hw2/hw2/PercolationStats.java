package hw2;

public class PercolationStats {
    private int[] openedsites;
    private final int time;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should larger than 0");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("T should larger than 0");
        }
        openedsites = new int[T];
        time = T;
        for (int i = 0; i < T; i++) {
            Percolation model = pf.make(N);
            while (!model.percolates()) {
                int randrow = edu.princeton.cs.introcs.StdRandom.uniform(N);
                int randcol = edu.princeton.cs.introcs.StdRandom.uniform(N);

                model.open(randrow, randcol);
            }
            openedsites[i] = model.numberOfOpenSites();
        }
    }

    public double mean() {
        return edu.princeton.cs.introcs.StdStats.mean(openedsites);
    }

    public double stddev() {
        return edu.princeton.cs.introcs.StdStats.stddev(openedsites);
    }

    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(time);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev()) / Math.sqrt(time);
    }
}
