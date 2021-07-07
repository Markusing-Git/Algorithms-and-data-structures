package algorithms.sorters;

/**
 * Selection sort is a easy and intuitive sorting algorithm.
 * The algorithm iterates through the array searching for the next smallest item
 * and placing it where it belongs. It does so on every index until all items have been correctly sorted.
 *
 * Number of comparisons: Best O(n^2), Worst O(n^2)
 * Number of exchanges: Best O(n), Worst O(n)
 * Stable: No
 *
 * @author mIngemarsson
 */
public class SelectionSort {

    /**
     * This method is a generic selection sort
     * @param arr will be sorted in increasing order.
     */
    public static <T extends Comparable<T>> void sort(T[] arr){
        int minIndex=0, n = arr.length;
        for(int index=0; index<n-1; index++){
            minIndex=index;
            for(int i=index+1; i<n; i++){
                if(arr[i].compareTo(arr[minIndex])<0)
                    minIndex=i;
            }
            T temp = arr[index];
            arr[index] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
