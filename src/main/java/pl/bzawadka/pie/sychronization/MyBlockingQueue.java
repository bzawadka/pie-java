package pl.bzawadka.pie.sychronization;

import java.util.LinkedList;
import java.util.Queue;

/** unlimited size blocking queue */
public class MyBlockingQueue {
    private Queue queue = new LinkedList();

    public synchronized void add(Object item) {
        queue.add(item);
        notify();
    }

    public synchronized Object take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.remove();
    }

    //------- let's call it a test: -----------
    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue();

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
            queue.add(logAdded("ala"));
            queue.add(logAdded("ma"));
            queue.add(logAdded("kota"));
            queue.add(logAdded("a kot"));
            queue.add(logAdded("ma"));
            queue.add(logAdded("ale"));
        }).start();
    }

    private static Object logAdded(String item) {
        System.out.println("added: " + item);
        return item;
    }

    private static void logTaken(Object item) {
        System.out.println("taken: " + item);
    }
}
