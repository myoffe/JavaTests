package producer_consumer;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 06/06/2013
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class Producer implements Runnable {

    private final SharedQueue queue;

    public Producer(SharedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        /*
            If not stop_requested:
            1. Write some data to queue
            2. Sleep a random amount of time (0 < t < 1sec)
            3. Repeat
         */

        Random rand = new Random();

        try {
            while  (!Thread.interrupted()) {
                int data = rand.nextInt(100);
                queue.write(data);

                int sleepDuration = rand.nextInt(1000) + 500;
                Thread.sleep(sleepDuration);
            }
        } catch (InterruptedException e) {
            System.out.format("%d Interrupted\n", Thread.currentThread().getId());
        }
    }
}
