package pl.bzawadka.pie.sychronization;

import java.util.LinkedList;
import java.util.Queue;

/** blocking queue with size limit, similar to http://tutorials.jenkov.com/java-concurrency/blocking-queues.html */
public class MyBlockingQueueWithLimit {
    private Queue queue = new LinkedList<>();
    private int limit = 3;

    public synchronized void put(Object item) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized Object take() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        Object item = queue.remove();
        notifyAll();
        return item;
    }

    //------- let's call it a test: -----------
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueueWithLimit queue = new MyBlockingQueueWithLimit();

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
