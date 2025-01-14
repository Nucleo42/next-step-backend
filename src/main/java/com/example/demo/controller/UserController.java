package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;


@RestController
@RequestMapping("/api/users")
public class UserController {
    // A simple in-memory map to store users for demonstration
    private Map<Long, User> userMap = new HashMap<>();
    private Long currentId = 1l;

    // GET endpoint to retrieve a user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userMap.get(id); // Return the user with the given ID
    }

    // GET endpoint to retrieve all users
    @GetMapping
    public Collection<User> getAllUsers() {
        return userMap.values(); // Return all users
    }

    // POST endpoint to add a new user
    @PostMapping
    public String createUser(@RequestBody User user) {
        user.setId(currentId++);
        userMap.put(user.getId(), user);
        return String.format("User created successfully! Id: %d, Username: %s, E-mail: %s", user.getId(), user.getName(), user.getEmail());
    }

    // DELETE endpoint to remove a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        if (userMap.containsKey(id)) {
            userMap.remove(id); // Remove user from the map
            return "User deleted successfully!";
        } else {
            return "User not found!";
        }
    }
}