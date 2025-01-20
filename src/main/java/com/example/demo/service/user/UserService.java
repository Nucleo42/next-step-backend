package com.example.demo.service.user;

import com.example.demo.domain.user.Category;
import com.example.demo.domain.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Ensure password is encoded before saving
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String name, String password) {
        return userRepository.findByName(name)
                .filter(user -> user.getPassword().equals(password)); // Compare plain text passwords (consider encoding)
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void addCategoryToUser(Long userId, Category category) {
        Optional<User> userOpt = userRepository.findById(userId);
        userOpt.ifPresent(user -> {
            Set<Category> categories = user.getCategories();
            if (!categories.contains(category)) {
                categories.add(category);
                user.setCategories(categories);
                userRepository.save(user);
            }
        });
    }

    public void removeCategoryFromUser(Long userId, Category category) {
        Optional<User> userOpt = userRepository.findById(userId);
        userOpt.ifPresent(user -> {
            Set<Category> categories = user.getCategories();
            if (categories.contains(category)) {
                categories.remove(category);
                user.setCategories(categories);
                userRepository.save(user);
            }
        });
    }
}
