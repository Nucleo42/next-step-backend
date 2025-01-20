package com.example.demo.controller.auth;

import com.example.demo.domain.user.User;
import com.example.demo.domain.requests.LoginRequest;
import com.example.demo.domain.requests.RegisterRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtConfig;
import com.example.demo.service.auth.AuthService;
import com.example.demo.service.auth.JwtService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          UserRepository userRepository, PasswordEncoder passwordEncoder,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
