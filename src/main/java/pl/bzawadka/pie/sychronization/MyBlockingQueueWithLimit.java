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
                reportTaken(queue.take());
                reportTaken(queue.take());
                reportTaken(queue.take());
                reportTaken(queue.take());
                reportTaken(queue.take());
                reportTaken(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                queue.put(reportAdded("ala"));
                queue.put(reportAdded("ma"));
                queue.put(reportAdded("kota"));
                queue.put(reportAdded("a kot"));
                queue.put(reportAdded("ma"));
                queue.put(reportAdded("ale"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static Object reportAdded(String item) {
        System.out.println("put: " + item);
        return item;
    }

    private static void reportTaken(Object item) {
        System.out.println("taken: " + item);
    }
}
