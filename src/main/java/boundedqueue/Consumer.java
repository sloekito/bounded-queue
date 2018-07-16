package boundedqueue;

import java.util.logging.Logger;

public class Consumer<T> implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());

    private Queue<T> queue;
    
    public Consumer(Queue<T> q){
        this.queue = q;
    }

    @Override
    public void run() {
        try{
            while(true) {
                Object item = queue.dequeue();
                LOGGER.info("Consumed " + item.toString());

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}