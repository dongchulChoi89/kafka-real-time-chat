package com.choi.kafka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // make WebSocket application to become a STOMP broker for connected STOMP clients
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat").setAllowedOriginPatterns("*").withSockJS(); // webSocket(sockJS) endpoint for chat client
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // STOMP message(STOMP frame) whose destination begins with '/app' goes to @MessageMapping method in @Controller class
        registry.enableSimpleBroker("/topic"); // STOMP message(STOMP frame) whose destination begins with 'topic' goes to built-in message broker for subscriptions and broadcasting
    }
}
