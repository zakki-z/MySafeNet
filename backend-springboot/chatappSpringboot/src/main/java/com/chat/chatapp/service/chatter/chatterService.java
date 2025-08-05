package com.chat.chatapp.service.chatter;

public interface chatterService {
    public String getAllChatter();
    public String insert(String Status,String username,String email,String password,String phone,String CreatedAt,String lastSeen);
    public String delete(Long id);
}
