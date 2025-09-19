package com.example.mysafenet.entity;

import com.example.mysafenet.entity.user.Chatter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity

@Table(name = "block_list")
public class BlockList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blocker_user_id")
    private Chatter blocker;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blocked_user_id")
    private Chatter blockedUser;
    private LocalTime blockedAt;
}
