package boundedqueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue<T> implements ProducerConsumerQueue<T> {
    private LinkedList storage = new java.util.LinkedList();
    private int capacity;
    private static final Logger LOGGER = Logger.getLogger(Queue.class.getName());

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
                LOGGER.info(String.format("Queue empty, size: %d", this.size()));
                wait();
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
        msg = (T) storage.remove();
        notifyAll();
        return msg;
    }

    /**
     * Add object to storage, block till the queue is non-full. Notify any waiting
     * threads that there is an object available.
     */
    public synchronized void enqueue(T o) {
        while (isFull()) {
            try {
                LOGGER.info(String.format("Queue full, size: %d", this.size()));
                wait();
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }
        storage.add(o);
        notifyAll();
    }

    /*
     * Helper method for getting the size of the queue
     */ 
    public int size() {
        return storage.size();
    }

    /*
     * Helper method for when the queue reaches capacity
     */
    boolean isFull() {
        return this.storage.size() >= this.capacity;
    }

}