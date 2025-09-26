package com.example.mysafenet.entity;

import com.example.mysafenet.entity.user.Chatter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.*;
import java.util.UUID;


@Entity

@Table(name = "block_list")
@Data
public class BlockList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocker_user_id",nullable = false)
    private Chatter blocker;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blocked_user_id",nullable = false)
    private Chatter blockedUser;
    @NotNull
    private LocalDateTime blockedAt;
}
