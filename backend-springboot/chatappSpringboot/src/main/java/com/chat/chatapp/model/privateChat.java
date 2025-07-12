package com.chat.chatapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "privatechat")
@Setter
@Getter
public class privateChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name="sender_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_privatechat_sender"))
    private Chatter sender;

    @ManyToOne
    @JoinColumn(
            name = "receiver_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name="fk_privatechat_reciever")
    )
    private Chatter receiver;

    private Boolean isBlocked;

    @OneToMany (mappedBy="privateChat")
    private List<messages> messages;
}
