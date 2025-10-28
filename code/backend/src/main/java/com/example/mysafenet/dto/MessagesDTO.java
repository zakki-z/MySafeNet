package com.example.mysafenet.dto;

import com.example.mysafenet.entity.Chat;
import com.example.mysafenet.entity.enums.MessageType;
import com.example.mysafenet.entity.user.Chatter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessagesDTO(
        UUID id,
        @NotNull Chat chat,
        @NotNull Chatter sender,
        String content,
        boolean isRead,
        boolean isDeleted,
        boolean isEdited,
        boolean isSent,
        boolean isDelivered,
        boolean isSeen,
        @NotNull
        LocalDateTime sentAt,
        LocalDateTime deliveredAt,
        LocalDateTime seenAt,
        LocalDateTime editedAt,
        LocalDateTime deletedAt,
        @NotNull MessageType messageType
        ) {
}
