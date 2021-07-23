package data_structures;

/**
 * A queue is a data-structure that maintains entities with add, remove and find operations.
 *
 * This implementation is based on the FIFO (first in first out) principle. The internal data structure
 * is a circular generic array with the ability to grow and shrink when needed.
 *
 * @author mIngemarsson
 */
public class ArrayQueue<E> {
    private int front, rear, size, maxSize;
    private E[] data;

    /**
     * Constructor method, will create an empty queue
     * @param initialMaxSize is the initial size of the array.
     */
    public ArrayQueue(int initialMaxSize){
        size = 0;
        front = 0;
        maxSize = initialMaxSize;
        rear = maxSize-1;
        data = (E[]) new Object[maxSize];
    }

    /**
     * This method will add an element to the queue
     * @param element to be added to the queue.
     */
    public boolean offer(E element){
        if(size == maxSize) {
            reallocate();
        }
        rear = (rear+1) % maxSize;
        data[rear] = element;
        size++;
        return true;
    }

    /**
     * This method will not modify the size or order of the queue only return the front element.
     * @return the front element of the queue.
     */
    public E peek(){
        if(size==0) return null;
        return data[front];
    }

    /**
     * This method will return and remove the front element of the queue.
     * If the queue is empty the method returns null
     * @return the front element of the queue.
     */
    public E poll(){
        if(size==0){
            return null;
        }else {
            size--;
            E element = data[front];
            front =(front+1) % maxSize;
            if(size <= maxSize/4){
                shrink();
            }
            return element;
        }
    }

    @Override
    public String toString() {
        String info = "data: [";
        for(int i=front; i<=rear; i++){
            info += data[i] + ", ";
            if(i==rear)
                info = info.substring(0, info.lastIndexOf(','));
        }
        info += "]\nfront: " + front + " rear: " + rear +
                "\nmaxSize: " + maxSize;

        return info;
    }


    // helper methods **************************************************************************************************

    // array doubles in size for each resize to minimize time complexity
    private void reallocate() {
        int newMaxSize = 2 * maxSize;
        E[] newData = (E[]) new Object[newMaxSize];
        int j = front;
        for (int i = 0; i < size; i++) {
            newData[i] = data[j];
            j = (j + 1) % maxSize;
        }
        front = 0;
        rear = size - 1;
        maxSize = newMaxSize;
        data = newData;
    }

    private void shrink(){
        int newMaxSize = maxSize/2;
        E[] newData = (E[]) new Object[newMaxSize];
        int j = front;
        for (int i = 0; i < size; i++) {
            newData[i] = data[j];
            j = (j + 1) % maxSize;
        }
        front = 0;
        rear = size - 1;
        maxSize = newMaxSize;
        data = newData;
    }
}
