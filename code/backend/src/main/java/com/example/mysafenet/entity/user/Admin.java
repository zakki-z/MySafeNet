package com.example.mysafenet.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity

@Table(name = "admins")
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    @Column(name = "admin_role")
    private String adminRole;


}
