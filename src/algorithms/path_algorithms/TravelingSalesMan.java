package algorithms.path_algorithms;

import java.util.BitSet;

/**
 * Greedy solution to the traveling salesman problem
 * The problem: Given a list of cities and the distances between each pair of cities,
 * what is the shortest possible route that visits each city exactly once and returns to the origin city?
 *
 * The algorithm will choose the shortest path available to the next unvisited node until all cities has been
 * visited.
 */
public class TravelingSalesMan {
    public static int minDistanceGreedy(int[][] w,int[] route){
        BitSet setT = new BitSet(w.length);
        setT.set(0, w.length);
        int curr = 0, distance=0;

        for(int i=1;i<route.length; i++) {
            setT.clear(curr);
            int next = next(w[curr], setT);
            distance +=w[curr][next];
            route[i] = next;
            curr = next;
        }
        return distance;
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
