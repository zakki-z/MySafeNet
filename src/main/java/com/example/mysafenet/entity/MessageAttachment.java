package com.example.mysafenet.entity;

import com.example.mysafenet.entity.user.Chatter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data

@Table(name = "message_attachment")
public class MessageAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "messages_id")
    private Messages messages;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private long fileSize;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_user_id")
    private Chatter sender;
    private LocalDateTime uploaded_at;
}
