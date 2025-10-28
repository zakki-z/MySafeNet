package com.example.mysafenet.controllers;

import com.example.mysafenet.entity.user.User;
import com.example.mysafenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    public UserService userService;
    @GetMapping()
    public String getAllUsers() {
        return userService.getAllUsers().toString();
    }
    @GetMapping("/{id}")
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }
    @PostMapping()
    public User addNewUser(User user) {
        return userService.addNewUser(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
