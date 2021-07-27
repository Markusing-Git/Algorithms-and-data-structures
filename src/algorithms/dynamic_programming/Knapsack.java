package algorithms.dynamic_programming;

/**
 * 0 - 1 Knapsack problem:
 * What is the highest value of items you can pack in a knapsack without reaching the given max value.
 * You can only pack one or none of the given items, in other words no repetitions are allowed.
 *
 * @author mIngemarsson
 */
public class Knapsack {
    public static void main(String[] args){
        System.out.println(maxValue(20,new int[]{13,11,10,5,4},new int[]{9,8,7,4,3})); //28
        System.out.println(maxValue(117,new int[]{13,11,10,5,4},new int[]{9,8,7,4,3})); //43
        System.out.println(maxValue(30,new int[]{10,22},new int[]{10,21})); //22
    }

    /**
     * This method is a bottom-up algorithm that returns the highest packable value based on the max weight.
     * @param maxWeight Max weight of the knapsack
     * @param values value of each item
     * @param weights weight of each item
     * @return highest packable value
     */
    public static int maxValue(int maxWeight, int[] values, int[] weights){
        if(maxWeight<=0) return 0;
        int nbrOfItems = values.length;
        int[][] output = new int[nbrOfItems+1][maxWeight+1];

        // bottom up algorithm
        for(int i=0; i<=nbrOfItems; i++){
            for(int j=0; j<=maxWeight; j++){
                if(i==0 || j==0)
                    output[i][j] = 0;
                else if (weights[i - 1] <= j)
                    output[i][j] = Math.max(values[i - 1] + output[i - 1][j - weights[i - 1]], output[i - 1][j]);
                else
                    output[i][j] = output[i - 1][j];
            }
        }

        return output[nbrOfItems][maxWeight];
    }
}
