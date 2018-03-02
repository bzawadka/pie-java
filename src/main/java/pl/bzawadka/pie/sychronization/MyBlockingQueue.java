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
            queue.add(reportAdded("ala"));
            queue.add(reportAdded("ma"));
            queue.add(reportAdded("kota"));
            queue.add(reportAdded("a kot"));
            queue.add(reportAdded("ma"));
            queue.add(reportAdded("ale"));
        }).start();
    }

    private static Object reportAdded(String item) {
        System.out.println("added: " + item);
        return item;
    }

    private static void reportTaken(Object item) {
        System.out.println("taken: " + item);
    }
}
