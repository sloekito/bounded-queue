package boundedqueue;

public class ProducerConsumerService {

    public static void main(String[] args) {
        //Creating queue of size 5
        Queue<Object> queue = new Queue<>(5);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        //starting producer to produce messages in queue
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();
        //starting consumer to consume messages from queue
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
    }

}