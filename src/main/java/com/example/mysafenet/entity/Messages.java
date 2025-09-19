package com.example.mysafenet.entity;

import com.example.mysafenet.entity.user.Chatter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data

@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_user_id")
    private Chatter sender;
    private String content;
    private boolean isRead;
    private boolean isDeleted;
    private boolean isEdited;
    private boolean isSent;
    private boolean isDelivered;
    private boolean isSeen;
    private LocalDateTime sentAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime seenAt;
    private LocalDateTime editedAt;
}
