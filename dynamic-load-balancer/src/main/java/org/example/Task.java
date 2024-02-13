package org.example;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    // The task's run logic should be implemented here
    public void execute() {
        System.out.println("Executing Task on " + Thread.currentThread().getName());
        // Task's complex logic goes here...
    }
}
