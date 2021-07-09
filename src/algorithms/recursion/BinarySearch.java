package algorithms.recursion;

/**
 * Recursive Binary search. The algorithm will find the target with a performance of O(log n) by recursively
 * eliminating half of the elements on each method call.
 *
 * @author mIngemarsson
 */
public class BinarySearch {
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T target){
        return binarySearch(arr,target,0, arr.length-1);
    }

    private static <T extends Comparable<T>> int binarySearch(T[] arr, T target, int first, int last){
        if(first>last) {
            return -1;
        }else {
            int middle = (first + last)/2;
            int compRes = target.compareTo(arr[middle]);
            if (compRes == 0) {
                return middle;
            } else if (compRes < 0) {
                return binarySearch(arr, target, first, middle-1);
            } else {
                return binarySearch(arr, target, middle+1, last);
            }
        }
    }

    public static void main(String[] args){
        Integer[] array = {0,4,8,12,16,20,24,30,32,45};
        int idx = binarySearch(array, 20);
        System.out.println(idx);
    }
}
