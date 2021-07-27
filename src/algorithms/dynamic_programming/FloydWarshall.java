package algorithms.dynamic_programming;

/**
 * Floyd warshall is a path algorithm that will find all the shortest paths in a directed weighted graph.
 * This implementation uses a bottom-up dynamic programming approach to solve the problem.
 *
 * Performance: Best O(|V|^3), Average O(|V|^3), Worst O(|V|^3)
 * Worst-case space complexity: O(|V|^2)
 * (V is number of vertices).
 *
 * @author mIngemarsson
 */
public class FloydWarshall {
    public static void main(String[] args) {
        int X = 999; // represents infinity

        // A weighted directional graph represented by a matrix
        int[][] w = {
                {X, 2, X, X, X, -1, X, X},
                {X, X, 2, 2, -4, X, X, X},
                {X, X, X, X, 3, X, X, 1},
                {X, X, X, X, 4, 1, X, X},
                {X, X, X, X, X, -2, 7, X},
                {X, X, X, X, X, X, 5, X},
                {X, X, X, X, X, X, X, 6},
                {3, -1, X, X, X, X, X, X}};

        int[][] dist = floydWarshall(w);

        System.out.println("Shortest path from 2-3 is " + shortestPath(dist,2,3 ));
        System.out.println("Shortest path from 1-7 is " + shortestPath(dist,1,7 ));
        System.out.println("Shortest path from 7-6 is " + shortestPath(dist,7,6));
        System.out.println("Shortest path from 2-8 is " + shortestPath(dist,2,8 ));
    }

    /**
     * This method will find the shortest paths of all vertices in a weighted directional graph. Works on both positive
     * and negative edge weights.
     * @param graph weighted adjacency matrix
     * @return 2d adjency matrix with shortest paths.
     */
    public static int[][] floydWarshall(int[][] graph){
        int n = graph.length;
        int[][] output = new int[n][n];
        // copy matrix
        for(int i=0; i<n; i++) {
            System.arraycopy(graph[i],0, output[i], 0, n);
        }
        // set diagonal to 0
        for(int i=0; i<n; i++){
            output[i][i] = 0;
        }
        //find shortest paths
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    output[i][j] = Math.min(output[i][j], output[i][k] + output[k][j]);
                }
            }
        }
        return output;
    }

    /**
     * Get shortest path between 2 given nodes
     * @throws IllegalArgumentException
     * @param graph weighted adjacency matrix with shortest paths
     * @param u from
     * @param v to
     * @return total weight of shortest path
     */
    public static int shortestPath(int[][] graph, int u, int v){
        u=u-1;
        v=v-1;
        if((u<0 || u > graph.length) || (v<0 || v> graph.length))
            throw new IllegalArgumentException("Graph does not include given vertices");
        return graph[u][v];
    }
}
