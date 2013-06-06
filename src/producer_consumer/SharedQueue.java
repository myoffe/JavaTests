package producer_consumer;

import java.util.LinkedList;
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
public class SharedQueue {

    private Queue<Integer> queue = new LinkedList<Integer>();
    private Lock lock = new ReentrantLock();
    Random rand = new Random();

    public Lock getLock() {
        return lock;
    }

    public synchronized int read() {

        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        // Get value from queue
        int data = queue.remove();
        System.out.format("%s: Read data from queue: %d\n", Thread.currentThread().getId(), data);
        print();

        // Notify another thread that it can have the lock now
        notify();

        return data;
    }

    public synchronized void write(int data) {
        // Write the data
        queue.add(data);
        System.out.format("%s: Wrote data to queue: %d\n", Thread.currentThread().getId(), data);
        print();

        // Notify another thread that it can have the lock now
        notify();

    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private void print() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int s : queue) {
            str.append(s);
            str.append(", ");
        }
        str.append("]");

        System.out.println("Queue: " + str.toString());
    }

}
