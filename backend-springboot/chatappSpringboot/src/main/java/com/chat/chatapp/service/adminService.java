package com.chat.chatapp.service;
import com.chat.chatapp.model.admin;

import java.util.List;

public interface adminService {
    public List<String> getAdminCredentials();
    public String getAdminCredentialsTest(String EMAIL);
    public String insert(String Status,String username,String email,String password,String phone);
    public String select();
    public String DeleteAdminById(Long id);
}
