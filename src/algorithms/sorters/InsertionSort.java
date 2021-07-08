package algorithms.sorters;

/**
 * The Insertion sort algorithm takes an inserted item and shifts it down the array until
 * the item is in it´s rightful position.
 * The Algorithm will be as fast as O(n) when an item is inserted in
 * an already sorted array. It is also well suited for smaller arrays and often used in
 * combination with a divide-and-conquer algorithm.
 *
 * Number of comparisons: Best O(n), Worst O(n^2)
 * Number of exchanges: Best O(n), Worst O(n^2)
 * Stable: yes
 *
 * @author mIngemarsson
 */
public class InsertionSort {

    /**
     * This method is a generic Insertion sort
     * @param arr will be sorted in increasing order.
     */
    public static <T extends Comparable<T>> void sort(T[] arr){
        T data;
        int dataIndex;
        for(int currIdx=0; currIdx< arr.length; currIdx++){
            data = arr[currIdx];
            dataIndex = currIdx;
            while (dataIndex>0 && (data.compareTo(arr[dataIndex-1])<0)){
                arr[dataIndex] = arr[dataIndex-1];
                dataIndex--;
            }
            arr[dataIndex] = data;
        }
    }
}
