import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private int count;
	private double[] result;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0) 
			throw new java.lang.IllegalArgumentException();
		
		count = trials;
		result = new double[count];
		
		for (int i = 0; i < count; i++) {
			Percolation perc = new Percolation(n);
			int total = 0;
			while (!perc.percolates()) {
				int p = StdRandom.uniform(1, n + 1);
				int q = StdRandom.uniform(1, n + 1);
				if (!perc.isOpen(p, q)) {
					perc.open(p, q);
					total++;
				}
			}
			double percent = (double) total / (n * n);
			result[i] = percent;
		}
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(result);
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(result);
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean()-((1.96*stddev())/Math.sqrt(count));
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean()+((1.96*stddev())/Math.sqrt(count));
	}

	// test client (described below)
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(N, T);

        String confidence = "[" + p.confidenceLo() + "], [" + p.confidenceHi() + "]";
        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println("95% confidence interval = " + confidence);
	}
}
