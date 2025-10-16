package com.example.mysafenet.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Data
@Table(name = "users")
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID uuid;

    @NotNull
    @Column(nullable = false, unique = true, length = 50)
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
    private String profilePicture;

    @NotNull
    @Column(nullable = false)
    private String status;

    // Spring Security UserDetails implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return role based on discriminator value
        String role = this.getClass().getAnnotation(DiscriminatorValue.class).value();
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "ACTIVE".equalsIgnoreCase(status);
    }
}