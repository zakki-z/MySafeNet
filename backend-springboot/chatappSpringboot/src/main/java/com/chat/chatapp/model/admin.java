package com.chat.chatapp.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class admin extends User{

    public admin() {
    }
    public admin (String username,
                    String password,
                    String email,
                    String phoneNumber,
                    String profilePicture,
                    String status)
    {}

}
