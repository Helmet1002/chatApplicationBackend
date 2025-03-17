package com.chatApp.backend.service;

import com.chatApp.backend.model.ChatMessage;
import com.chatApp.backend.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;


    public ChatMessage saveMessage(ChatMessage chatMessage){

        chatMessage.setChatRoomId(generateChatRoomid(chatMessage.getSender(),chatMessage.getReceiver()));
        return chatMessageRepository.save(chatMessage);
    }

    public String generateChatRoomid(String user1,String user2){
        //this method generates a roomid that is used for commn for private chats
    return Stream.of(user1, user2).sorted().collect(Collectors.joining("_"));
}
}
