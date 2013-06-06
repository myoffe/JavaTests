package producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
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

    static final int MAX_ELEMENTS = 50;

    private Queue<Integer> queue = new LinkedList<Integer>();
    final private Lock lock = new ReentrantLock();
    final private Condition notEmpty = lock.newCondition();
    final private Condition notFull = lock.newCondition();

    private boolean stopping = false;

    Random rand = new Random();

    public Lock getLock() {
        return lock;
    }

    public void stop() {
        lock.lock();
        stopping = true;
        notEmpty.signalAll();
        notFull.signalAll();
        lock.unlock();
    }

    public int read() throws InterruptedException{
        lock.lock();

        int data = 0;
        try {
            while (queue.isEmpty() && !stopping) {
                notEmpty.await();
            }

            if (!stopping) {
                // Get value from queue
                data = queue.remove();
                System.out.format("(Reader%s) Read data from queue: %d\n", Thread.currentThread().getId(), data);
                print();

                notFull.signal();
            }
        } catch (InterruptedException e) {
            throw e;
        }




        lock.unlock();

        return data;
    }

    public void write(int data) throws InterruptedException{
        lock.lock();

        try {
            while (queue.size() == MAX_ELEMENTS && !stopping) {
                notFull.await();
            }

            if (!stopping) {
                // Write the data
                queue.add(data);
                System.out.format("(Writer%s) Wrote data to queue: %d\n", Thread.currentThread().getId(), data);
                print();

                notEmpty.signal();
            }
        } catch (InterruptedException e) {
            throw e;
        }

        lock.unlock();
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
