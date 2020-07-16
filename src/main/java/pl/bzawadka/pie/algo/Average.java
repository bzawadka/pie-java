package pl.bzawadka.pie.algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Average {
    private int sum = 0;
    private int counter = 0;
    private Collection<Integer> numbers = new ArrayList<>();
    private Lock lock = new ReentrantLock();

    public void addNumber(int number) {
        numbers.add(number);

        sum += number;
        counter++;
    }

    public double average() {
        if (counter == 0) return 0;
        return (double) sum / counter;
    }

    public double average2() {
        int sum = 0;
        if (numbers != null && !numbers.isEmpty()) {
            for (Integer number : numbers) {
                sum += number;
            }
            return (double) sum / numbers.size();
        }
        return -1;
    }

    public double average3() {
        if (numbers == null || numbers.isEmpty()) return 0;

        return (double) numbers.stream().collect(Collectors.summingInt(i -> i)).intValue() / numbers.size();
    }

    public double average4() {
        if (numbers == null || numbers.isEmpty()) return 0;

        return numbers.stream().collect(Collectors.averagingInt(i -> i)).doubleValue();
    }

    public double average5() {
        if (numbers == null || numbers.isEmpty()) return 0;

        return numbers.stream().mapToInt(i -> i).average().getAsDouble();
    }

    public void threadSafeAddNumber(int number) {
        lock.lock();
        try {
            sum += number;
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public double threadSaveAverage() {
        lock.lock();
        lock.lock();
        try {
            if (counter == 0) return 0;
            return (double) sum / counter;
        } finally {
            lock.unlock();
        }
    }

}
