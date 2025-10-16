package com.example.mysafenet.controllers;

import com.example.mysafenet.entity.user.Admin;
import com.example.mysafenet.entity.user.Chatter;
import com.example.mysafenet.repository.AdminRepository;
import com.example.mysafenet.repository.ChatterRepository;
import com.example.mysafenet.repository.UserRepository;
import com.example.mysafenet.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ChatterRepository chatterRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
        }
        if (request.email() != null && userRepository.existsByEmail(request.email())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }

        Admin admin = new Admin();
        admin.setUsername(request.username());
        admin.setPassword(passwordEncoder.encode(request.password()));
        admin.setEmail(request.email());
        admin.setFullName(request.fullName());
        admin.setAge(request.age());
        admin.setPhoneNumber(request.phoneNumber());
        admin.setStatus("ACTIVE");
        admin.setAdminRole(request.role() != null ? request.role() : "GENERAL_ADMIN");

        adminRepository.save(admin);

        var jwtToken = jwtService.generateToken(admin);
        return ResponseEntity.ok(Map.of(
                "token", jwtToken,
                "userType", "ADMIN",
                "username", admin.getUsername()
        ));
    }

    @PostMapping("/register/chatter")
    public ResponseEntity<?> registerChatter(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
        }
        if (request.email() != null && userRepository.existsByEmail(request.email())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }

        Chatter chatter = new Chatter();
        chatter.setUsername(request.username());
        chatter.setPassword(passwordEncoder.encode(request.password()));
        chatter.setEmail(request.email());
        chatter.setFullName(request.fullName());
        chatter.setAge(request.age());
        chatter.setPhoneNumber(request.phoneNumber());
        chatter.setStatus("ACTIVE");
        chatter.setChatterRole(request.role() != null ? request.role() : "REGULAR_USER");

        chatterRepository.save(chatter);

        var jwtToken = jwtService.generateToken(chatter);
        return ResponseEntity.ok(Map.of(
                "token", jwtToken,
                "userType", "CHATTER",
                "username", chatter.getUsername()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        var user = userRepository.findByUsername(request.username())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        String userType = user.getClass().getAnnotation(jakarta.persistence.DiscriminatorValue.class).value();

        return ResponseEntity.ok(Map.of(
                "token", jwtToken,
                "userType", userType,
                "username", user.getUsername()
        ));
    }

    // DTOs
    public record RegisterRequest(
            String username,
            String password,
            String email,
            String fullName,
            String phoneNumber,
            int age,
            String role
    ) {}

    public record LoginRequest(String username, String password) {}
}