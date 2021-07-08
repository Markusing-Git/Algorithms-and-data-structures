package algorithms.sorters;

/**
 * The Quick sort algorithm takes an arbitrarily selected value from the array and uses it as a pivot.
 * It then sorts all values less then the pivot to the left side of the array and all the
 * higher values to the right side. This process is repeated recursively on the divided subarrays until the
 * entire array is sorted. Statistically the split is expected to be 50-50 if the pivot has a randomly selected value,
 * which makes Quick sort O(n log n). With that being said, if the split is e.g. 90-10 or 100-0 repeatedly
 * Quick sort gives a very poor performance.
 *
 * Number of comparisons: Best O(n log n), Average O(n log n), Worst O(n^2)
 *
 * @author mIngemarsson
 *
 */
public class QuickSort {

    /**
     * This method is a generic Quick sort
     * @param arr will be sorted in increasing order.
     */
    public static <T extends Comparable<T>> void sort(T[] arr ){
        int first = 0, last = arr.length-1;
        sort(arr, first, last);
    }

    private static <T extends Comparable<T>> void sort(T[] arr, int first, int last){
        if(first < last){
            int pivotIdx = partition(arr,first,last);
            sort(arr, first, pivotIdx-1);
            sort(arr, pivotIdx+1, last);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr,int first, int last){
        T pivotValue = arr[first];
        int up = first, down = last;
        do{
            while((arr[up].compareTo(pivotValue)<=0) && up<last){
                up++;
            }
            while((arr[down].compareTo(pivotValue)>0) && down>first){
                down--;
            }
            if(up<down){
                swap(arr, up, down);
            }
        }while(up<down);
        swap(arr, first, down);
        return down;
    }

    private static <T extends Comparable<T>> void swap(T[] arr, int a,  int b){
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
