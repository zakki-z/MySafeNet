package com.chat.chatapp.repository;

import com.chat.chatapp.model.admin;

import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface adminDAO {
    public Optional<admin> findOneByEmail( String email);
    public String insert( admin admin);
    public List<admin> select();
    public String DeleteAdminById(Long id);
}
