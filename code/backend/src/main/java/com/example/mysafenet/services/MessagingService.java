package com.example.mysafenet.services;

import com.example.mysafenet.entity.Messages;
import com.example.mysafenet.entity.user.Chatter;
import com.example.mysafenet.entity.user.User;
import com.example.mysafenet.exceptions.MessageNotFoundException;
import com.example.mysafenet.exceptions.UserNotFoundException;
import com.example.mysafenet.repository.MessagesRepository;
import com.example.mysafenet.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MessagingService {
    @Autowired
    private  MessagesRepository messagesRepository;
    @Autowired
    private UserRepository userRepository;
    @SendToUser("/topic/public")
    public Messages sendMessage(@Payload Messages message, @NotNull UUID userID){
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userID));
        message.setSender((Chatter) user);
        message.setSentAt(LocalDateTime.now());
        return messagesRepository.save(message);
    }
    public Messages receiveMessage(@Payload Messages message, UUID userID){
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userID));
        message.setSender((Chatter) user);
        message.setDeliveredAt(LocalDateTime.now());
        return messagesRepository.save(message);
    }
    @Transactional
    public Messages updateMessage(@NotNull @Payload Messages message, @NotNull UUID userID,UUID messageId) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userID));
        Messages existingMessage = messagesRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException("Message not found: " + message.getId()));

        existingMessage.setEditedAt(LocalDateTime.now());
        existingMessage.setSender((Chatter) user);
        existingMessage.setContent(message.getContent());

        return messagesRepository.save(existingMessage);
    }

    public void deleteById(UUID messageId) {
        messagesRepository.deleteById(messageId);
    }
}