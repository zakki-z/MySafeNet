package com.example.mysafenet.repository;

import com.example.mysafenet.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {


}
