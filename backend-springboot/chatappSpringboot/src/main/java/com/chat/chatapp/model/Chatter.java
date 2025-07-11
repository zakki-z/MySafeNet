package com.chat.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "chatter")
public class Chatter extends User{
    private String CreatedAt;
    private String LastSeen;

    public String getCreatedAt() {
        return CreatedAt;
    }
    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
    public String getLastSeen() {
        return LastSeen;
    }
    public void setLastSeen(String lastSeen) {
        LastSeen = lastSeen;
    }
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
        this.CreatedAt = createdAt;
        this.LastSeen = lastSeen;
    }
}
