package org.example;

import java.util.concurrent.ConcurrentHashMap;

 class DataSynchronizer {

    private ConcurrentHashMap<String, String> database;

    public DataSynchronizer() {
        database = new ConcurrentHashMap<>();
    }

    public synchronized void updateData(String key, String value) {
        if(key == null || value == null) throw new IllegalArgumentException("Neither key nor value can be null.");
        database.put(key, value);
        System.out.println("Data updated: " + key + " => " + value);
    }

    public void synchronizeData() {
        // Simulate data synchronization to a remote server
        database.forEach((key, value) -> {
            // Here, implement the API call to synchronize data
            System.out.println("Synchronizing: " + key + " => " + value);
            // Imagine this is a REST API call that syncs the data
        });
    }
}
