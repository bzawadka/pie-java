package pl.bzawadka.pie.sychronization;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducersAndConsumers {
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        BlockingQueue q = new LinkedBlockingDeque(THREAD_POOL_SIZE);
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }

    static class Producer implements Runnable {
        private final BlockingQueue queue;
        Producer(BlockingQueue q) { queue = q; }
        Random random = new Random();
        public void run() {
            try {
                while (true) { queue.put(produce()); }
            } catch (InterruptedException ex) { }
        }
        Object produce() {
            System.out.println("producing...");
            return random.nextInt(1000);
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue queue;
        Consumer(BlockingQueue q) { queue = q; }
        public void run() {
            try {
                while (true) { consume(queue.take()); }
            } catch (InterruptedException ex) { }
        }
        void consume(Object x) { System.out.println("consuming..." + x); }
    }
}
