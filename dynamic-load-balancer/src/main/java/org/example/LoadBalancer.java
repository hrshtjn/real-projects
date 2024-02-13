package org.example;

public interface LoadBalancer {
    void addServer(Server server);
    Server getNextServer();
}
