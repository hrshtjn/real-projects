package com.stockworks.stockdatasimulatorservice.config;

import com.stockworks.stockdatasimulatorservice.service.StockSimulatorServiceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class StockSimulatorConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(tutorialHandler(), "/stocks")
                .setAllowedOrigins("*");

    }

    @Bean
    WebSocketHandler tutorialHandler() {
        return new StockSimulatorServiceHandler();
    }
}
