package com.example.mysafenet.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity

@Table(name = "chatter")
@DiscriminatorValue("CHATTER")
public class Chatter extends User{
    @Column(name = "chatter_role")
    private String chatterRole;
}
