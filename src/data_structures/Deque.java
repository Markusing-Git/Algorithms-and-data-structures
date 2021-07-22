package data_structures;

/**
 * A queue is a data-structure that maintains entities with add, remove and find operations.
 *
 * This implementation is a generic double-ended queue (Deque or Dequeue). Therefore it has the ability to
 * add and remove items on either side of the queue while having the benefit of a time complexity of
 * O(1) on all it's operations. This comes with the disadvantage of using extra space for the node references.
 */
public class Deque<E> {
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> front;
    private Node<E> rear;
    private int size;

    /**
     * Constructor method, will create an empty queue.
     */
    public Deque() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * This method will add an element to the front of the queue
     * @param element to be added to the queue.
     */
    public void offerFirst(E element){
        if(size==0) {
            rear = new Node<E>(element, null, null);
            front = rear;
        } else{
            front.prev = new Node<E>(element, front, null);
            front = front.prev;
        }
        size++;
    }

    /**
     * This method will add an element to the end of the queue
     * @param element to be added to the queue
     */
    public void offerLast(E element){
        if(size==0) {
            rear = new Node<E>(element, null, null);
            front = rear;
        } else{
            rear.next = new Node<E>(element, null, rear);
            rear = rear.next;
        }
        size++;
    }

    /**
     * This method will remove an element from the front of the queue
     * @return the removed element.
     */
    public E pollFirst(){
        if(size==0)
            return null;
        E item = front.data;
        front = front.next;
        front.prev = null;
        size--;
        return item;
    }

    /**
     * This method will remove an element from the end of the queue
     * @return the removed element.
     */
    public E pollLast(){
        if(size==0)
            return null;
        E item = rear.data;
        rear = rear.prev;
        rear.next = null;
        size--;
        return item;
    }

    /**
     * This method will not modify the size or order of the queue only return the first element.
     * @return the first element of the queue.
     */
    public E peekFirst() {
        if(size==0)
            return null;
        return front.data;
    }

    /**
     * This method will not modify the size or order of the queue only return the last element.
     * @return the last element of the queue.
     */
    public E peekLast() {
        if(size==0)
            return null;
        return rear.data;
    }

    /**
     *
     * @return true if empty false if not.
     */
    public boolean empty(){
        return size==0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> p = front;
        if (p != null) {
            while (p.next != null) {
                sb.append(p.data.toString());
                sb.append(" ==> ");
                p = p.next;
            }
            sb.append(p.data.toString());
        }
        sb.append("]");
        return sb.toString();
    }
}
