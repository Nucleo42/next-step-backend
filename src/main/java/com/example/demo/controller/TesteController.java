package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TesteController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // Only accessible to users with the "ADMIN" role
    public String adminRoute() {
        return "Hello Admin! You have access to this route.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')") // Only accessible to users with the "USER" role
    public String userRoute() {
        return "Hello User! You have access to this route.";
    }
}
