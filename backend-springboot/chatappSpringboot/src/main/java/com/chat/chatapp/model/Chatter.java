package com.chat.chatapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "chatter")
@Getter
@Setter
public class Chatter extends User{
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private String createdAt;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private String lastSeen;

    @OneToMany(mappedBy = "chatter",cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<blockList> blockList;
    public Chatter() {
    }
    public Chatter (String username,
                    String password,
                    String email,
                    String phoneNumber,
                    String profilePicture,
                    String status,
                    String createdAt,
                    String lastSeen)
    {
        super.setUsername(username);
        super.setPassword(password);
        super.setEmail(email);
        super.setPhoneNumber(phoneNumber);
        super.setProfilePicture(profilePicture);
        super.setStatus(status);
        this.createdAt = createdAt;
        this.lastSeen = lastSeen;
    }
}
