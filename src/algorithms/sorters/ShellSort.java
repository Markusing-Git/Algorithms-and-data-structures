package algorithms.sorters;


/**
 * The Shell sort algorithm uses a divide-and-conquer approach by dividing the array into many subarrays
 * and sorting them with insertion sort. Finally the algorithm can sort the entire array with a performance of O(n).
 * To divide the initial array the algorithm uses a gap size. Empirical studies have shown that using a initial gap
 * of n/2 and then dividing the gap by 2.2 will give a O(n^(7/6)) or O(n^(5/4)) but there is no theoretical
 * evidence to back this up.
 * Because the algorithm has a space-complexity of O(1) (O(n) including the array) it is often use in embedded systems.
 *
 * Number of comparisons: Best O(n^(7/6)), Average O(n^(5/4)), Worst O(n^2)
 * Stable: No
 *
 * @author mIngemarsson
 */
public class ShellSort {

    /**
     * This method is a generic Shell sort
     * @param arr will be sorted in increasing order.
     */
    public static <T extends Comparable<T>> void sort(T[] arr){
        int gap = arr.length/2, dataIndex;
        T data;
        while (gap>0){
            for(int index = gap; index<arr.length; index++) {
                data = arr[index];
                dataIndex = index;
                while (dataIndex > gap - 1 && (data.compareTo(arr[dataIndex - gap]) < 0)) {
                    arr[dataIndex] = arr[dataIndex - gap];
                    dataIndex -= gap;
                }
                arr[dataIndex] = data;
            }
            if(gap==2)
                gap=1;
            else
                gap=(int) (gap/2.2);
        }
    }
}
