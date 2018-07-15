package boundedqueue;
import java.util.LinkedList;


public class Queue<T> implements ProducerConsumerQueue<T> {
    private LinkedList storage = new LinkedList();
    private int capacity;
    private int size;

    Queue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * If the queue is empty then the block till the queue is non-empty
     */
    public synchronized T dequeue() {
        T msg = null;
        while (storage.isEmpty()) {
            try {
                System.out.println("Queue empty");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        msg = (T) storage.remove();
        this.size--;
        return msg;
    }
 
    /**
     * Add object to storage, block till the queue is non-full. 
     * Notify any waiting threads that there is an object available.
     */
    public synchronized void enqueue(T item) {
        while (isFull()) {
            try {
                System.out.println("Queue full");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        storage.add(item);
        this.size++;
        notifyAll();
    }

    /*
    * Helper method for getting the size of the queue
    */
    public int size() {
        return size;
    }

    /* 
    * Helper method for when the queue reaches capacity
    */
    private boolean isFull() {
        return this.size >= this.capacity;
    }


}