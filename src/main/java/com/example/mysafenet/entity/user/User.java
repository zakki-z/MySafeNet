package com.example.mysafenet.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data

@Table(name = "users")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long UUID;
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
