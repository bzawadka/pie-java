package pl.bzawadka.pie.memory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemoryLeak {
    public static void main(String[] args) {
        TaskCreator taskCreator = new TaskCreator();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            for (int i = 0; i < 100000; i++) {
                taskCreator.createTask();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class TaskCreator {
        private TaskList taskList;

        public TaskCreator() {
            this.taskList = new TaskList();
        }

        public void createTask() {
            taskList.addTask(new Task());
        }
    }


    private static class TaskList {
        private Deque<Task> tasks = new ArrayDeque<Task>();

        public void addTask(Task task) {
            tasks.add(task);
            tasks.peek().execute();//Memory leak!, reaplace with poll()
        }
    }

    private static class Task {
        private Object[] array = new Object[1000];

        public void execute() {
            //dostuff
        }
    }
}