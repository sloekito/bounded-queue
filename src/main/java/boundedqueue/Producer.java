package boundedqueue;

import java.util.logging.Logger;

public class Producer<T> implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

    private Queue<Object> queue;
    private int numberOfElements;
    public Producer(Queue<Object> q, int numberOfElements) {
        this.queue = q;
        this.numberOfElements = numberOfElements;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < this.numberOfElements; i++) {
            
                LOGGER.info("Producing " + i);
                queue.enqueue(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
