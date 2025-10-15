package com.example.mysafenet.config;

import com.example.mysafenet.entity.Messages;
import com.example.mysafenet.entity.enums.MessageType;
import com.example.mysafenet.entity.user.Chatter;
import com.example.mysafenet.repository.ChatterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final ChatterRepository ChatterRepository;
    private final MessagesRepository messagesRepository;

    @EventListener
    public void handelWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            log.info("User Disconnected : " + username);
            Chatter sender = ChatterRepository.findByUsername(username)
                    .orElseThrow(() ->new EntityNotFoundException("chatter not found"));
            var chatMessage = Messages.builder()
                    .messageType(MessageType.LEAVE)
                    .sender(sender)
                    .sentAt(LocalDateTime.now())
                    .build();
            // TODO Save messages
           //messagesRepository.save(chatMessage);
        }
    }
}
