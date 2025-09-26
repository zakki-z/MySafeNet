package com.example.mysafenet.entity;

import com.example.mysafenet.entity.user.Chatter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data

@Table(name = "message_attachment")
public class MessageAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messages_id")
    private Messages messages;
    @NotNull
    private String fileName;
    @NotNull
    private String fileUrl;
    @NotNull
    private String fileType;
    private long fileSize;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_user_id")
    private Chatter sender;
    @NotNull
    private LocalDateTime uploadedAt;
}
