package boundedqueue;

public class Producer<T> implements Runnable {

    private Queue<Object> queue;

    public Producer(Queue<Object> q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Producing " + i);
                queue.enqueue(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
