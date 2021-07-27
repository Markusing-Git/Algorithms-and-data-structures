package algorithms.dynamic_programming;

/**
 * This is a top-down dynamic programming algorithm to find the number of unique paths a MxN grid can have.
 *
 * @author mIngemarsson
 */
public class UniquePaths {
    public static void main(String[] args){
        int m=12, n=6;
        System.out.println("Number of unique paths for a " + m + "x" + n + " grid is: " + getUniquePaths(m,n));
    }

    /**
     * Top-down algorithm to get the number of unique paths for MxN grid.
     * @param m rows
     * @param n columns
     * @return number of unique paths
     */
    public static int getUniquePaths(int m, int n){
        int[][] tab = new int[m+1][n+1];
        return getUniquePaths(m,n, tab);
    }

    public static int getUniquePaths(int m, int n, int[][] tab){
        if(m==0 || n==0)
            return 0;
        if(tab[m][n]!=0)
            return tab[m][n];
        return tab[m][n] = 1 + getUniquePaths(m-1,n,tab) + getUniquePaths(m,n-1,tab);
    }
}
