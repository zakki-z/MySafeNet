package com.chat.chatapp.Controller.admin;

import com.chat.chatapp.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chat.chatapp.service.adminService;

@RestController
public class adminLoginController {
    @Autowired
    private final adminService adminServiceImpl;
    public adminLoginController(adminService adminServiceImpl) {
        this.adminServiceImpl = adminServiceImpl;
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/api/admin")
    public String Cerdentinel()
    {
        return adminServiceImpl.getAdminCredentialsTest("ayoub@gmail.com");
    }

    @GetMapping("/api/admin/insert")
    public String save()
    {
        adminServiceImpl.insert("offlinse","ayoub7","ayoub77@gmail.com","asdasd","1232456");
        return "201";
    }

    @GetMapping("/api/admin/select")
    public String Select()
    {
        return adminServiceImpl.select();
    }
    @DeleteMapping("/api/admin")
    public String Delete()
    {
        Long id = 21L;
        return adminServiceImpl.DeleteAdminById(id);
    }


}
