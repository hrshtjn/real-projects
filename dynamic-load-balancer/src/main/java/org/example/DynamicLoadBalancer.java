package org.example;

import java.io.IOException;

public class DynamicLoadBalancer {

    private LoadBalancer loadBalancer;

    public DynamicLoadBalancer(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    // Adds a server to the load balancer
    public void addServer(Server server) {
        loadBalancer.addServer(server);
        //server.start(); // Start the server's task processing
    }

    // Submits a task to the cluster to be processed
    public void submitTask(Task task) {
        Server server = loadBalancer.getNextServer();
        if (server != null) {
            server.addTask(task);
            server.start();
        } else {
            System.out.println("No servers available to process the task");
        }
    }

    public static void main(String[] args) throws IOException {
        DynamicLoadBalancer cluster = new DynamicLoadBalancer(new RoundRobinLoadBalancer());
        Server server1 = new Server("Server1");
        Server server2 = new Server("Server2");

        // Adding servers to the cluster
        cluster.addServer(server1);
        cluster.addServer(server2);

        // Submitting tasks to the cluster
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            cluster.submitTask(task);
            try {
                Thread.sleep(1000); // Simulate delay between task submissions
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
