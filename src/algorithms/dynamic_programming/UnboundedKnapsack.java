package algorithms.dynamic_programming;

/**
 * Unbounded Knapsack problem:
 * What is the highest value of items you can pack in a knapsack without reaching the given max value.
 * You can pack several of the same item, in other words repetitions are allowed.
 *
 * @author mIngemarsson
 *
 */
public class UnboundedKnapsack {
    public static void main(String[] args){
        System.out.println(maxValue(20,new int[]{13,11,10,5,4},new int[]{9,8,7,4,3})); //28
        System.out.println(maxValue(117,new int[]{13,11,10,5,4},new int[]{9,8,7,4,3})); //169
        System.out.println(maxValue(30,new int[]{10,22},new int[]{10,21})); //30
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
        int[] output = new int[maxWeight+1];

        // bottom up algorithm
        for(int i=0; i<=maxWeight; i++){
            for(int j=0; j<nbrOfItems; j++) {
                if (weights[j] <= i) {
                    output[i] = Math.max(values[j] + output[i - weights[j]], output[i]);
                }
            }
        }

        return output[maxWeight];
    }
}
