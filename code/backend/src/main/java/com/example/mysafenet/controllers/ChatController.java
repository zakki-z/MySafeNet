package com.example.mysafenet.controllers;

import com.example.mysafenet.entity.Messages;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendToUser("/topic/public")
    public Messages sendMessage(@Payload Messages message){
        return message;
    }
    @MessageMapping("/chat.receiveMessage")
    @SendToUser("/topic/public")
    public Messages receiveMessage(@Payload Messages message){
        return message;
    }
}
