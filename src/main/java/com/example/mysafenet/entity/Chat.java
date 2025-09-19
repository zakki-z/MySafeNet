package com.example.mysafenet.entity;


import com.example.mysafenet.entity.user.Chatter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity

@Table(name = "chat")
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String name;
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatType chatType;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_user_id", nullable = false)
    private Chatter creator;
    @NotNull
    private LocalDateTime createdAt;

}
enum ChatType{
    group,
    privateChat
}