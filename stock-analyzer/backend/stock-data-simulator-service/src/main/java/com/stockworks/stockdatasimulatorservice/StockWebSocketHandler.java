package com.stockworks.stockdatasimulatorservice;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Random;

@Controller
public class StockWebSocketHandler {
    @MessageMapping("/stocks")
    @SendTo("/topic/stockData")
    public StockData sendStockData() throws InterruptedException {
        Random random = new Random();
        String[] stockNames = {"AAPL", "GOOGL", "MSFT", "AMZN", "FB"};

        while (true) {
            String name = stockNames[random.nextInt(stockNames.length)];
            double price = 100 + random.nextDouble() * 100; // Random price between 100 and 200
            Thread.sleep(1000); // Simulate real-time updates every second
            return new StockData(name, price);
        }
    }
}
