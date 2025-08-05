package com.chat.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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
    {
        super.setUsername(username);
        super.setPassword(password);
        super.setEmail(email);
        super.setPhoneNumber(phoneNumber);
        super.setProfilePicture(profilePicture);
        super.setStatus(status);
    }

    public <E> admin(@Email String email, @Size(min = 6) String password, ArrayList<E> es) {
        super();
    }
}
