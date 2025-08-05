package com.chat.chatapp.service.impl;


import com.chat.chatapp.model.admin;
import com.chat.chatapp.repository.adminDAO;
import com.chat.chatapp.service.adminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class adminServiceImpl implements adminService {

    @Autowired
    private final adminDAO adminDAOImpl;
    @Autowired
    private ObjectMapper objectMapper;


    public adminServiceImpl(adminDAO adminDAOImpl) {
        this.adminDAOImpl = adminDAOImpl;
    }

    @Override
    public List<String> getAdminCredentials(){
        Optional<admin> adminOPt = adminDAOImpl.findOneByEmail("<EMAIL>");
        if(adminOPt.isPresent())
        {
            admin admin = adminOPt.get();
            String email = admin.getEmail();
            String password = admin.getPassword();
            List<String> credentials = List.of(email,password);
            return credentials;
        }else
            return Collections.emptyList();
    }
    @Override
    public String getAdminCredentialsTest(String EMAIL){
        Optional<admin> adminOPt = adminDAOImpl.findOneByEmail("EMAIL");
        if(adminOPt.isPresent())
        {
            admin admin = adminOPt.get();
            String email = admin.getEmail();
            String password = admin.getPassword();
            List<String> credentials = List.of(email,password);
            return email + " " + password;
        }else
            return "no admin found";
    }
    @Override
    public String insert(String Status,String username,String email,String password,String phone) {
        admin admin = new admin(username,password,email,phone,null,Status);
        System.out.println(admin.getUsername());
        System.out.print("hello");
        return adminDAOImpl.insert(admin);
    }
    @Override
    public String select() {
        try
        {
            List<admin> admin = adminDAOImpl.select();
            return objectMapper.writeValueAsString(admin);
        }catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "{\"error\": \"Failed to serialize admin list\"}";
        }
    }
    @Override
    public String DeleteAdminById(Long id) {
        return adminDAOImpl.DeleteAdminById(id);
    }
}
