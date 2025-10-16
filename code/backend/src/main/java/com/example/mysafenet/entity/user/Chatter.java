package com.example.mysafenet.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "chatters")
@DiscriminatorValue("CHATTER")
@Data
@EqualsAndHashCode(callSuper = true)
public class Chatter extends User {

    @Column(name = "chatter_role")
    private String chatterRole;
}