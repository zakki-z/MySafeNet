package com.example.mysafenet.entity;


import com.example.mysafenet.entity.user.Chatter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity

@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private type type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_user_id")
    private Chatter creator;
    private LocalTime createdAt;

}
enum type{
    group,
    privateChat
}