package boundedqueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueTest<T> {
    private Queue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue(5);
    }

    @Test
    public void testEnqueue() {

        queue.enqueue(1);
        Assert.assertEquals(queue.size(), 1);
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        Object item = queue.dequeue();
        Assert.assertEquals(queue.size(), 0);
        Assert.assertEquals(item, 1);
    }

    @Test
    public void testEnqueueMaxCapacity() throws InterruptedException {
        Queue<Object> queue = new Queue<>(5);
        int numberOfElements = 10;
        Producer producer = new Producer(queue, numberOfElements);
        Thread mainRunner = new Thread(producer);

        new Thread(producer).start();

    
        mainRunner.start();
    
        Thread.sleep(5000);
        // Queue is in a waiting state when it is full
        Assert.assertEquals(Thread.State.WAITING, mainRunner.getState());
        mainRunner.interrupt();

    }


}