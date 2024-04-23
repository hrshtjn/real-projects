package com.stockworks.stockdatasimulatorservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockworks.stockdatasimulatorservice.StockData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class StockSimulatorServiceHandler implements WebSocketHandler {

    private WebSocketSession session;
    private Timer timer;
    private ObjectMapper objectMapper;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established on session: {}", session.getId());

        this.session = session;
        this.objectMapper = new ObjectMapper();

        // Start a timer to send data every second
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendData();
            }
        }, 0, 1000);
    }

    private void sendData() {
        try {
            // Send a message to the WebSocket session
            Random random = new Random();
            String[] stockNames = {"AAPL", "GOOGL", "MSFT", "AMZN", "FB"};
            String name = stockNames[random.nextInt(stockNames.length)];
            double price = 100 + random.nextDouble() * 100;
            StockData stockData = new StockData();
            stockData.setName(name);
            stockData.setPrice(price);
            String jsonString = objectMapper.writeValueAsString(stockData);
            session.sendMessage(new TextMessage(jsonString));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String tutorial = (String) message.getPayload();
        log.info("Message: {}", tutorial);
        session.sendMessage(new TextMessage("Started processing tutorial: " + session + " - " + tutorial));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("Completed processing tutorial: " + tutorial));

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Exception occured: {} on session: {}", exception.getMessage(), session.getId());

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Connection closed on session: {} with status: {}", session.getId(), closeStatus.getCode());

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
