package com.example.mysafenet.repository;

import com.example.mysafenet.entity.user.Chatter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChatterRepository extends JpaRepository <Chatter, UUID>{
    Optional<Chatter> findByUsername(String username);

}
