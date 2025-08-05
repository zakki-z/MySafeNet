package com.chat.chatapp.Controller.chatter;

import com.chat.chatapp.service.chatter.chatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class chatterController {
    @Autowired
    private final chatterService ChatterServiceImpl;

    public chatterController(chatterService ChatterServiceImpl) {
        this.ChatterServiceImpl = ChatterServiceImpl;
    }

    @GetMapping("/api/chatter")
    public String getAllChatter()
    {
        return ChatterServiceImpl.getAllChatter();
    }

    @PutMapping("/api/chatter")
    public String insert(String Status,String username,String email,String password,String phone,String CreatedAt,String lastSeen)
    {
         Status= "offline";
         username="lala";
         email="<EMAIL>";
         password="<PASSWORD>";
         phone="01065487956";
         CreatedAt="2021-09-20";
         lastSeen="09:44";

        return ChatterServiceImpl.insert(Status,username,email,password,phone,CreatedAt,lastSeen);
    }

    @DeleteMapping("/api/chatter")
    public String Delete(Long id)
    {
        return ChatterServiceImpl.delete(2L);
    }
}
