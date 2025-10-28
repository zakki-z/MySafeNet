package com.example.mysafenet.controllers;

import com.example.mysafenet.entity.Messages;
import com.example.mysafenet.services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ChatController {
    @Autowired
    private MessagingService messagingService;

    @MessageMapping("/chat.sendMessage/{id}")
    public Messages sendMessage(@Payload Messages message, UUID userID){
        return messagingService.sendMessage(message,userID);
    }
    @MessageMapping("/chat.receiveMessage/{id}")
    @SendToUser("/topic/public")
    public Messages receiveMessage(@Payload Messages message,UUID userID){
        return messagingService.receiveMessage(message, userID);
    }
    @MessageMapping("/chat.updateMessage/{id}")
    public Messages updateMessage(@Payload Messages message, UUID userID,UUID messageId) {
        return messagingService.updateMessage(message,userID,messageId);
    }
    @MessageMapping("/chat.deleteMessage/{id}")
    public void deleteMessage(@Payload UUID messageId) {
        messagingService.deleteById(messageId);
    }
}
