package com.chat.chatapp.service.impl;

import com.chat.chatapp.model.Chatter;
import com.chat.chatapp.repository.Chatter.chatterDAO;
import com.chat.chatapp.service.chatter.chatterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

@Service
public class ChatterServicImpl implements chatterService {

    @Autowired
    private final chatterDAO chatterDAOImpl;
    @Autowired
    private ObjectMapper objectMapper;

    public ChatterServicImpl(chatterDAO chatterDAOImpl,
                             ObjectMapper objectMapper) {
        this.chatterDAOImpl = chatterDAOImpl;
        this.objectMapper = objectMapper;
    }
    @Override
    public String getAllChatter() {
        try {
            List<Chatter> chatter = chatterDAOImpl.SelectAllChetter();
            return objectMapper.writeValueAsString(chatter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to serialize chatter list\"}";
        }
    }

    @Override
    public String insert(String Status,String username,String email,String password,String phone,String CreatedAt,String lastSeen){
        try {
            Chatter chatter = new Chatter();
            chatter.setUsername(username);
            chatter.setPassword(password);
            chatter.setEmail(email);
            chatter.setPhoneNumber(phone);
            chatter.setStatus(Status);
            chatter.setCreatedAt(CreatedAt);
            chatter.setLastSeen(lastSeen);
            chatterDAOImpl.insert(chatter);
            return "201";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String delete(Long id) {
        return chatterDAOImpl.delete(id);
    }
}
