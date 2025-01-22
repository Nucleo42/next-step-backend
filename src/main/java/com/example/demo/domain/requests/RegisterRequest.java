package com.example.demo.domain.requests;

import com.example.demo.domain.user.Category;
import com.example.demo.domain.user.Role;
import lombok.Data;
import java.util.Set;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Set<Category> categories; // List of category names as strings
    private Role role;

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
