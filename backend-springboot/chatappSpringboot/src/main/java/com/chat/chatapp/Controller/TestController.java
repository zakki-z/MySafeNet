package com.chat.chatapp.Controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@PreAuthorize("hasRole('ADMIN')")
@RestController
public class TestController {

    @GetMapping("/api/test")
    public String test() {
        return "Spring Boot Application is running!";
    }

    @GetMapping("/api/test2")
    public String test2() {
        return "Spring Boot test 2 is running!";
    }
}