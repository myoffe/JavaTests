package producer_consumer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 06/06/2013
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    static int N_CONSUMERS = 8;
    static int N_PRODUCERS = 5;

    public static void main(String[] args) throws InterruptedException {

        SharedQueue queue = new SharedQueue();
        Set<Thread> threads = new HashSet<Thread>();

        // Create threads
        for (int i=0; i<N_CONSUMERS; i++) {
            threads.add(new Thread(new Consumer(queue)));
        }

        for (int i=0; i<N_PRODUCERS; i++) {
            threads.add(new Thread(new Producer(queue)));
        }

        // Make things happen
        for (Thread thread : threads) {
            thread.start();
        }

        //

        // Wait for a while
        Thread.sleep(10000);

        queue.stop();

        // Close the deal
        for (Thread thread : threads) {
            thread.interrupt();
            thread.join();
        }
    }
}
