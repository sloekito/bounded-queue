package boundedqueue;


public class Consumer<T> implements Runnable{

    private Queue<T> queue;
    
    public Consumer(Queue<T> q){
        this.queue = q;
    }

    @Override
    public void run() {
        try{

            while(true) {
                Object item = queue.dequeue();
                System.out.println("Consumed " + item.toString());

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

	private void consume(T dequeue) {
	}
}