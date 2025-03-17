package com.chatApp.backend.controller;


import com.chatApp.backend.model.ChatMessage;
import com.chatApp.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatAppController {


    @Autowired
   private ChatService chatService;


@Autowired
private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat.send")
    @SendTo("/topic/chat/{chatRoomId}")
    public ChatMessage sendMessage(@DestinationVariable String chatRoomId, @Payload ChatMessage chatMessage) {
        // Save message in the database
        System.out.println("received message in group chat");
        ChatMessage savedMessage = chatService.saveMessage(chatMessage);
        return savedMessage;
    }


    @MessageMapping("/chat.private")
    @SendTo("/users")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage) {
        // Save message in DB
        System.out.println("received message in group chat");

        ChatMessage savedMessage = chatService.saveMessage(chatMessage);

        // Define the private destination for the receiver
        String destination = "/queue/private/" + chatMessage.getReceiver();

        // Send message to the specific receiver only
        messagingTemplate.convertAndSend(destination, savedMessage);
    }
}

