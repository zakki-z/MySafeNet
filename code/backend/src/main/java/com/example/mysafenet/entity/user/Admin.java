package com.example.mysafenet.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admins")
@DiscriminatorValue("ADMIN")
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends User {

    @Column(name = "admin_role")
    private String adminRole;
}