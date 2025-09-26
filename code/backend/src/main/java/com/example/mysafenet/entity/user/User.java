package com.example.mysafenet.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data

@Table(name = "users")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID UUID;
    @NotNull
    @Column( nullable = false, unique = true, length = 50)
    private String username;
    @NotNull
    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = false)
    private String fullName;
    @Column(unique = false)
    private int age;
    @Column(unique = false)
    private String ProfilePicture;
    @NotNull
    @Column(nullable = false)
    private String Status;

}
