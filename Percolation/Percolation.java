import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF qu;
	private WeightedQuickUnionUF quBW;
	private boolean[][] id;
	private int total;
	private int source = 0;
	private int sink;
	
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) { 
		if (n <= 0) 
			throw new java.lang.IllegalArgumentException();
		qu = new WeightedQuickUnionUF(n * n + 2);
		quBW = new WeightedQuickUnionUF(n * n + 1);
		id = new boolean[n][n];
		sink = n * n + 1;
		total = 0;
	} 

	// open site (row, col) if it is not open already
    public    void open(int row, int col) {
    	Valid(row, col);
    	if (!isOpen(row, col)) {
    		id[row - 1][col - 1] = true;
    		  	
    		if (row == 1) {
    			qu.union(indexOfArr(row, col), source);
    			quBW.union(indexOfArr(row, col), source);
    		}
    		if (row == id.length) { 
    			qu.union(indexOfArr(row, col), sink);
    		}
    	
    		if (col < id.length && isOpen(row, col + 1)) {
    			qu.union(indexOfArr(row, col), indexOfArr(row, col + 1));
    			quBW.union(indexOfArr(row, col), indexOfArr(row, col + 1));
    		}
    		if (col > 1  && isOpen(row, col - 1)) {
    			qu.union(indexOfArr(row, col), indexOfArr(row, col - 1));
    			quBW.union(indexOfArr(row, col), indexOfArr(row, col - 1));
    		}
    		if (row < id.length && isOpen(row + 1, col)) {
    			qu.union(indexOfArr(row, col), indexOfArr(row + 1, col));
    			quBW.union(indexOfArr(row, col), indexOfArr(row + 1, col));  	
    		}
    		if (row > 1 && isOpen(row - 1, col)) {
    			qu.union(indexOfArr(row, col), indexOfArr(row - 1, col));
    			quBW.union(indexOfArr(row, col), indexOfArr(row - 1, col));
    		}
    	
    		total++;
    	}
    }
    
    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
    	Valid(row, col);
    	return (id[row - 1][col - 1] == true);
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
    	Valid(row, col);
    	return quBW.connected(source, indexOfArr(row, col));
    }
 
    // number of open sites
    public     int numberOfOpenSites() {
    	return total;
    }
    
    // does the system percolate?
    public boolean percolates() {
    	return qu.connected(source, sink);
    }

    private int indexOfArr(int r, int c) {
    	return id.length * (r - 1) + c;
    }
    
    private void Valid(int r, int c) {
    	if (r <= 0 || r > id.length)
    		throw new IndexOutOfBoundsException("Row value out of bounds: " + r);
    	if (c <= 0 || c > id.length)
    		throw new IndexOutOfBoundsException("Col value out of bounds: " + c);
    }
    
    // test client (optional)
    public static void main(String[] args) {
    	
    }
}
