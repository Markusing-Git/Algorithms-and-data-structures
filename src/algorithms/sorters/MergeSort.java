package algorithms.sorters;

/**
 * Merge sort is a divide-and-conquer algorithm. It recursively divides the given array into two subarrays
 * until the sizes of the subarrays reaches 1. Finally it merges the subarrays into a sorted array.
 * Merge sort is often used for sorting linked-lists if linear searches are required when indexing elements.
 *
 *  Number of comparisons: Best O(n), Worst O(n log n)
 *  Number of exchanges: O(n log n)
 *  Stable: yes
 *
 *  @author mIngemarsson
 */
public class MergeSort {

    /**
     * This method is a generic Merge sort
     * @param arr will be sorted in increasing order.
     */
    public static <T extends Comparable<T>> void sort(T[] arr){
        if(arr.length==1)
            return;
        int lengthA =arr.length/2, lengthB=arr.length-lengthA;
        T[] a = (T[]) new Comparable[lengthA], b = (T[]) new Comparable[lengthB];
        System.arraycopy(arr,0, a,0, lengthA);
        System.arraycopy(arr,lengthA, b,0, lengthB);
        sort(a);
        sort(b);
        merge(a,b,arr);
    }

    /**
     * This method will compare and merge subarray a and b into sorted array c
     * @param a first subarray
     * @param b second subarray
     * @param c output array
     */
    public static <T extends Comparable<T>> void merge(T[] a , T[] b, T[] c){
        int indexa=0, indexb=0, indexc=0;
        while (indexa<a.length && indexb<b.length){
            if(a[indexa].compareTo(b[indexb])<=0){
                c[indexc++] = a[indexa];
                indexa++;
            }else{
                c[indexc++] = b[indexb];
                indexb++;
            }
        }
        while (indexa<a.length) {
            c[indexc++] = a[indexa];
            indexa++;
        }
        while (indexb<b.length) {
            c[indexc++] = b[indexb];
            indexb++;
        }
    }
}
