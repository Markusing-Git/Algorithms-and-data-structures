package algorithms.dynamic_programming;

/**
 * Example of dynamic programming with a top-down and bottom-up solution.
 * The three algorithms solve the problem of finding the most "expensive" way (largest sum) from the bottom of the
 * pyramid to the top. The first algorithm is a recursive solution. It demonstrates the upside of using
 * dynamic programming solutions. The algorithm solves the problem, but is way slower than the
 * top-down and bottom-up solution. The pyramid problem is a simple but intuitive example of dynamic programming.
 *
 * @author mIngemarsson
 */
public class Pyramid {
    public static void main(String[] args){
        int[][] pyramid = {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};

        long startTime = System.nanoTime();
        System.out.println(recursiveSolve(pyramid));
        System.out.println("Time: " + (System.nanoTime() - startTime) + " ns");

        long startTime2 = System.nanoTime();
        System.out.println(dynamicTopDown(pyramid));
        System.out.println("Time: " + (System.nanoTime() - startTime2) + " ns");

        long startTime3 = System.nanoTime();
        System.out.println(dynamicBottomUp(pyramid));
        System.out.println("Time: " + (System.nanoTime() - startTime3) + " ns");

    }

    public static int recursiveSolve(int[][] pyramid){
        return recursiveSolve(pyramid,0,0);
    }

    public static int recursiveSolve(int[][] pyramid, int path, int level){
        if(level==pyramid.length)
            return 0;
        int best = 0;
        best = Math.max(best, Math.max(recursiveSolve(pyramid, path, level+1) +pyramid[level][path],
                recursiveSolve(pyramid,path+1, level+1) + pyramid[level][path]));
        return best;
    }

    /**
     * This method is a top-down solution to the pyramid problem. It uses a Table to store values at visited levels in
     * the recursion tree. By doing this the algorithm can check if a sub-problem already has been solved before it
     * tries to solve it. This can skip a lot of unnecessary computations and recursive calls which will optimize
     * the algorithm.
     */
    public static int dynamicTopDown(int[][] pyramid){
        int[][] table = {{0},{0,0},{0,0,0},{0,0,0,0},{0,0,0,0,0}};
        return dynamicTopDown(pyramid, table, 0,0);
    }


    private static int dynamicTopDown(int[][] pyramid, int[][] table, int path, int level){
        if(level==pyramid.length){
            return 0;
        }
        if(table[level][path]!=0) {
            return table[level][path];
        }
        table[level][path] = Math.max(dynamicTopDown(pyramid,table, path, level+1) + pyramid[level][path],
                dynamicTopDown(pyramid,table,path+1, level+1) + pyramid[level][path]);
        return table[level][path];
    }


    /**
     * This method is a bottom-up solution to the pyramid problem. The algorithm starts with solving the smallest
     * sub-problems, then it builds solutions upwards in the pyramid to solve bigger sub-problems until the whole problem
     * has been solved.
     */
    public static int dynamicBottomUp(int[][] pyramid) {
        int best = 0;
        for(int i = pyramid.length-1; i>=0; i--){
            for(int j=0; j<pyramid[i].length-1; j++){
                best = Math.max(pyramid[i][j] + pyramid[i-1][j], pyramid[i][j+1] + pyramid[i-1][j]);
                pyramid[i-1][j] = best;
            }
        }
        return pyramid[0][0];
    }
}
