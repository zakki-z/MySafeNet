package com.chat.chatapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Fixed typos: startegy -> strategy, GeneratedType -> GenerationType
    private long id;
    @Column( nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(unique = true)
    private String ProfilePicture;

    @Column(nullable = false)
    private String Status;

}