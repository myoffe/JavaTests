package producer_consumer;

import java.util.Queue;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 06/06/2013
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class Consumer implements Runnable {

    private final SharedQueue queue;

    public Consumer(SharedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        /*
            If not stop_requested:
            1. If queue is not empty, pop and print a value from it
            1a. otherwise, wait until data is availble
            2. Repeat
         */

        Random rand = new Random();

        while  (!Thread.interrupted()) {
            int data = queue.read();

            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) { break; }
        }
    }
}
