package com.example.mysafenet.repository;

import com.example.mysafenet.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    Optional<Messages> findById(UUID messageId);


    void deleteById(UUID messageId);
}
