package pl.bzawadka.pie.sychronization;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** blocking queue with size limit and nice Lock with Conditions, similar to example from {@link java.util.concurrent.locks.Condition} */
public class MyBlockingQueueWithLock {
    private Queue queue = new LinkedList<>();
    private int limit = 3;

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    public void put(Object item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == limit) {
                notFull.await();
            }
            queue.add(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            Object item = queue.remove();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    //------- let's call it a test: -----------
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueueWithLock queue = new MyBlockingQueueWithLock();

        new Thread(() -> {
            try {
                logTaken(queue.take());
                logTaken(queue.take());
                logTaken(queue.take());
                logTaken(queue.take());
                logTaken(queue.take());
                logTaken(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                queue.put(logPut("ala"));
                queue.put(logPut("ma"));
                queue.put(logPut("kota"));
                queue.put(logPut("a kot"));
                queue.put(logPut("ma"));
                queue.put(logPut("ale"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static Object logPut(String item) {
        System.out.println("put: " + item);
        return item;
    }

    private static void logTaken(Object item) {
        System.out.println("taken: " + item);
    }
}
