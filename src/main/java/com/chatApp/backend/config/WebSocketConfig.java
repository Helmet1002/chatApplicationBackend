package com.chatApp.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker  // Enables WebSocket message handling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        System.out.println("received message in configureMessageBroker");
        registry.enableSimpleBroker("/topic"); // Enable broker
        registry.setApplicationDestinationPrefixes("/app"); // Prefix for @MessageMapping
        registry.setUserDestinationPrefix("/user");// Clients send messages here
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("received message in registerStompEndpoints");
        registry.addEndpoint("/ws-chat")   // Clients connect to this endpoint
                .setAllowedOrigins("*");
          //      .withSockJS();   // Enables SockJS fallback
    }
}

