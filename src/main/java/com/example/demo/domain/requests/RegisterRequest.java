package com.example.demo.domain.requests;

import lombok.Data;
import java.util.Set;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Set<String> categories; // List of category names as strings

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getCategories() {
        return categories;
    }
}
