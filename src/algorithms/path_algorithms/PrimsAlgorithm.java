package algorithms.path_algorithms;

import java.util.BitSet;

/**
 * Prims algorithm is a greedy algorithm that aims to find a minimum spanning tree for a weighted directional graph.
 * A greedy algorithm will solve a sub-problem by finding the locally best solution but excluding the big picture.
 * Greedy algorithms can be effective but might not result in the best solution.
 *
 * @author mIngemarsson
 */
public class PrimsAlgorithm {
    public static void main(String[] args) {
        int X = Integer.MAX_VALUE;

        // A weighted directional graph represented by a matrix
        int[][] w = {
                {	X, 2, X, X, X, 1, X, X},
                {	2, X, 2, 2, 4, X, X, X},
                {	X, 2, X, X, 3, X, X, 1},
                {	X, 2, X, X, 4, 1, X, X},
                {	X, 4, 3, 4, X, X, 7, X},
                {	1, X, X, 1, X, X, 5, X},
                {	X, X, X, X, 7, 5, X, 6},
                {	X, X, 1, X, X, X, 6, X}};

        int[] p = prim(w);
        System.out.println("Node A is a startnode");
        for(int i=1; i<p.length;i++)
            System.out.println("Node "+(char)(i+'A')+" connected through "+(char)(p[i]+'A'));
        int distance =0;
        for(int i=1;i<w.length;i++)
            distance+=w[i][p[i]];
        System.out.println("Total weight of the spanning tree: "+distance);
    }


    /**
     * This method will execute a prim algorithm.
     * @param w weighted directional graph
     * @return array where the index represents the node and data of the index represents connecting node.
     *          e.g. i = B, i[1] = A
     */
    private static int[] prim(int[][] w){
        int X = Integer.MAX_VALUE;
        int d[] = new int[w.length];
        int p[] = new int[w.length];
        BitSet setT = new BitSet(w.length);
        setT.set(0, w.length);
        for(int i = 1; i<w.length; i++){
            d[i] = X;
        }

        for (int i = 0; i < d.length - 1; i++) {
            int u = next(d, setT);
            setT.clear(u);
            for (int v = 0; v < w[u].length; v++) {
                if (setT.get(v) && w[u][v] < d[v]) {
                    d[v] = w[u][v];
                    p[v] = u;
                }
            }
        }
        return p;
    }

    private static int next(int[] d, BitSet setT) {
        int dMin = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < d.length; i++)
            if (d[i] < dMin && setT.get(i)) {
                dMin = d[i];
                minIndex = i;
            }
        return minIndex;
    }
}
