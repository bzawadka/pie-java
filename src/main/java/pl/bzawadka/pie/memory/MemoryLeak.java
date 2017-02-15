package pl.bzawadka.pie.memory;

import java.util.ArrayDeque;
import java.util.Deque;

public class MemoryLeak {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        final TaskCreator taskCreator = new TaskCreator(taskList);
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                taskCreator.createTask();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static class TaskCreator {
        private TaskList taskList;

        public TaskCreator(TaskList taskList) {
            this.taskList = taskList;
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