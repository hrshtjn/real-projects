package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Server {
    // Each server has a queue to hold tasks
    private Queue<Task> tasks = new LinkedList<>();
    private String name;

    public Server(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Add tasks to server's queue
    public synchronized void addTask(Task task) {
        tasks.add(task);
    }

    // Starts the server's processing of tasks
    public void start() {
        // Simulate task processing
        new Thread(() -> {
            //System.out.println(this.getName());
            while (true) {
                Task task;
                synchronized (this) {
                    while (tasks.isEmpty()) {
                        try {
                            wait(); // Waiting for a task to arrive
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.poll();
                }
                // Execute the task
                task.execute();
            }
        }, name).start();
    }
}
