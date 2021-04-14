package com.interested;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;

public class Percolation { // to-do: test this api with test client
    /*
    to-do - run configurations.
    a command that works - javac com/interested/Percolation.java
    ^ from directory: /gitroot/data-structures-and-algorithms-princeton/src>

     */

    //int [] N; // N represents the entire grid
    // re-make: store in grid
    int n; // input size given
    int [][] grid;
    int vTop = 0; // virtual top
    int vBottom; // virtual bottom
    WeightedQuickUnionUF unionFind;
    int numOpen; // number of open sites

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n <= 0) { // validation
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.grid = new int[n][n];
        this.numOpen = 0;
        unionFind = new WeightedQuickUnionUF(n * n + 2); // n + 2 components to include vTop and vBottom
        // todo when we initialize the quickfind, it creates an array with elements
        // ordered from 0 to n - 1. therefore, I should populate my grid so that it contains
        // numbers 1 to n-2. (0 and n-1 are for vtop and vbotom). what this means is I need
        // to find a way to track if a site is open or closed without making it negative
        // or positive.

        // By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site:
        /*N = new int [n + 2]; // size n + 2 because we start with 0 and end with n +1 as virtual points
        for(int i = 0; i < n + 2; i++ ) {
            N[i] = i * -1; // store every site as blocked
        } */
        // we don't care about the values in the 2d array aside from them being closed or open.
        // but we do want the indices going from 1 to n (as far as the user is concerned)
        // from the backend, we need to deal with arrays being zero indexed.
        int value = 1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                grid[i][j] = -1 * value; // each value in the grid is unique so we can use union-find and join algo
                value++;
            }
        }
        vBottom = value; // set vBottom to one past end
    }

    // opens the site (row, col) if it is not open already - done
    public void open(int row, int col) {
        validate(row, col);



        // call union on all open adjacent sides - how to determine which
        // four sides are adjacent sides?
        /*
        if on outside edge, four sides are limited
        i, j + 1
        i, j - 1
        i + 1, j
        i - 1, j
        where i is >= 1 and <= n and j >=1 and <= n

         */

        // pseudocode: set site as open. then, for each adjacent site, if is not open, call union on it:
        // if site is on top or bottom row and getting opened, also call union with vTop and vBottom
        // keep in mind, row and col have minues one because array is zero indexed
        if(grid[row - 1][col - 1] < 0) {
            grid[row - 1][col - 1] *= -1; // open site
            numOpen++;
        } else return; // we cannot open an already open site
        if(!(col + 1 > n))  {
            if(isOpen(row, col + 1)) {
                unionFind.union(grid[row - 1][col - 1], grid[row - 1][col - 1 + 1]);
            }
        }
        if(!(col - 1 < n))  {
            if(isOpen(row, col - 1)) {
                unionFind.union(grid[row - 1][col - 1], grid[row - 1][col - 1 - 1]);
            }
        }
        if(!(row - 1 < n))  {
            if(isOpen(row - 1, col)) {
                unionFind.union(grid[row - 1][col - 1], grid[row - 1 - 1][col - 1]);
            }
        }
        if(!(row + 1 > n))  {
            if(isOpen(row + 1, col)) {
                unionFind.union(grid[row - 1][col - 1], grid[row - 1 + 1][col - 1]);
            }
        }
        if(row == 1) {
            unionFind.union(grid[row][col], vTop);
        }
        else if(row == n) {
            unionFind.union(grid[row][col], vBottom);
        }

    }

    // is the site (row, col) open? - done
    public boolean isOpen(int row, int col) {
        validate(row, col);
        row--;
        col--;
        return grid[row][col] > 0;
    }

    // is the site (row, col) full? - done
    public boolean isFull(int row, int col) {
        validate(row, col);
        row--;
        col--;
        return grid[row][col] <= 0;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {

        // idea for this: make site negative if closed, positive if open
        return numOpen;

    }

    // does the system percolate?
    public boolean percolates() {

        //does find(virtual top) == find(virtual bottom) ?

        return unionFind.find(vTop) == unionFind.find(vBottom); // if in the same component, then they percolate
    }

    public void validate(int row, int col) {
        if( (row < 1 ) || (row > n ) ) {
            throw new IllegalArgumentException();
        }
        if ( (col < 1 ) || (col > n ) ) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        StdOut.println("beginning test");
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(1, 2);
        p.open(2, 3);
        StdOut.println("percolates? " + p.percolates());
        StdOut.println("num open:" + p.numberOfOpenSites());
        StdOut.println(" is full?" + p.isFull(1, 3));
        p.open(1, 3);
        StdOut.println(" is open?" + p.isOpen(1, 3));
        StdOut.println("percolates? " + p.percolates());
    }
}
