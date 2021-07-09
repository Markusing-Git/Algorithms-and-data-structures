package algorithms.sorters;

/**
 * Radix sort is a fast sorting algorithm for positive integer numbers. This implementation uses Counting sort.
 * Counting sort does not use any comparisons and has a O(n + k) (k is the largest digit) performance but it will
 * require O(k) extra memory if the largest digit is substantial.
 * The counting sort is repeated for every base exponential of the largest digit in the array until the entire
 * array is sorted.
 *
 * Number of exchanges: O(n * k) where n is the number of keys and k is the digit size of the largest key.
 *
 * @author mIngemarsson
 *
 */
public class RadixSort {

    /**
     * This method is a integer Radix sort
     * @param arr will be sorted in increasing order.
     */
    public static void sort(int[] arr){
        int max = getMax(arr);
        for(int baseExp=1; max/baseExp>0; baseExp*=10)
            countSort(arr, baseExp);
    }

    /**
     *
     * @param arr output array
     * @param baseExp the base exponential (position number) for the count sort iteration
     */
    private static void countSort(int[] arr, int baseExp){
        int[] count = new int[10];
        int[] out = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int d = getDigit(arr[i], baseExp);
            count[d]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int d = getDigit(arr[i], baseExp);
            out[--count[d]] = arr[i];
        }
        System.arraycopy(out, 0, arr, 0, arr.length);
    }

    // This method will mask out the right position number according to the base exponential.
    private static int getDigit(int number, int baseExp) {
        if(baseExp>0)
            number = number/baseExp;
        return number%10;
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for(int i=0; i<arr.length; i++){
            if(max<arr[i])
                max = arr[i];
        }
        return max;
    }
}
