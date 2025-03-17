package com.chatApp.backend.repository;

import com.chatApp.backend.model.ChatMessage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    // Fetch messages between two users
    List<ChatMessage> findByChatRoomId(String chatRoomId);
}
