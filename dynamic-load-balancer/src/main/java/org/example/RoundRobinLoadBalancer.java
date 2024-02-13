package org.example;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinLoadBalancer implements LoadBalancer{

    private List<Server> serverList = new ArrayList<>();
    private int index = 0;

    @Override
    public void addServer(Server server) {
        serverList.add(server);
    }

    @Override
    public Server getNextServer() {
        if (serverList.isEmpty()) {
            return null;
        }
        Server server = serverList.get(index);
        index = (index + 1) % serverList.size();
        return server;
    }
}
