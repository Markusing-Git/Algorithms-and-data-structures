package data_structures;

import java.util.Arrays;

/**
 * A Heap is an almost complete tree structure with the root key being the smallest or the
 * largest key in the dataset. Inserting and removing items are O(log n) operations.
 * The heap data-structure can be used as a priority queue or as a sorting mechanism by inserting and extracting
 * an array of unsorted items.
 *
 * This implementation is a min-heap, meaning it inserts the smallest key as root and extracts the smallest key first.
 * For sorting this implementation can become more effective by using a heapify algorithm for inserting items.
 * Items must implement the Comparable interface and have a predefined compareTo method.
 */
public class Heap<E extends Comparable<E>>{
    private int size;
    private E[] data;
    private int nrOfElements;

    /**
     * Constructor method, creates an empty heap
     * @param size initial size of heap. Will resize if needed.
     */
    public Heap(int size) {
        this.size = size;
        data = (E[]) new Comparable[size];
        nrOfElements = 0;
    }

    /**
     * This method will add a item to the heap, O(log n).
     * @param data to be added.
     * @return true if successful.
     */
    public boolean insert(E data){
        if(nrOfElements ==size)
            resize();
        int currIdx = nrOfElements;
        this.data[nrOfElements++] = data;
        while(currIdx!=0 && data.compareTo(this.data[(currIdx-1)/2])<0){
            E tmp = this.data[currIdx];
            this.data[currIdx] = this.data[(currIdx-1)/2];
            this.data[(currIdx-1)/2] = tmp;
            currIdx = (currIdx-1)/2;
        }
        return true;
    }

    /**
     * Removes the smallest item from the heap, O(log n)
     * @return removed item or null if empty.
     */
    public E extract(){
        if(nrOfElements ==0) return null;
        E extracted = this.data[0];
        int currIdx = 0;
        int child1 = 1, child2 = 2;
        this.data[0] = this.data[nrOfElements -1];
        while(child1< nrOfElements){
            int min = child1;
            if(child2< nrOfElements)
                min = data[child1].compareTo(data[child2])<0 ? child1 : child2;
            if(this.data[currIdx].compareTo(this.data[min])>0) {
                E tmp = this.data[currIdx];
                this.data[currIdx] = this.data[min];
                this.data[min] = tmp;
                currIdx=min;
            } else {
                break;
            }
            child1 = (2*currIdx)+1;
            child2 = child1+1;
        }
        nrOfElements--;
        return extracted;
    }

    @Override
    public String toString() {
        String info = "data:" + Arrays.toString(Arrays.copyOf(data, nrOfElements))
                + ", nextPos: " + nrOfElements + " Size: " + size;
        return info;
    }

    private void resize(){
        size*=2;
        data=Arrays.copyOf(data,size);
    }
}
