package com.chat.chatapp.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Setter
@Getter
public class ChatMessageDTO {
    private Long sender;
    private String content;
    private Long chatId;
    private TrayIcon.MessageType type;
}
