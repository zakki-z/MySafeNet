package com.chat.chatapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "chat_id",
            referencedColumnName="id",
            foreignKey = @ForeignKey(name = "fk_messages_chat")
    )
    private privateChat privateChat;

    @ManyToOne
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_messages_sender")
    )
    private Chatter chatter;

    private LocalDateTime sentAt;

    private Boolean isSeen;

    private Boolean isDelivred;

    private Boolean isDeleted;
    private Boolean isEdited;
    private String message;



}
