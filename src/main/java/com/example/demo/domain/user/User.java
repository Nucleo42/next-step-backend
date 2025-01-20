package com.example.demo.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user") // Specify a custom table name
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String email;

    private String password;

    private String role;


    @ElementCollection
    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    @CollectionTable(name = "user_categories", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "category")
    private Set<Category> categories = new HashSet<>();

    public String getRole() {
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Set<Category> getCategories() {
        if (categories == null) {
            categories = new HashSet<>();
        }
        return categories;
    }


    public void setCategories(Set<Category> categories) {
        if (categories != null) {
            this.categories = categories;
        } else {
            this.categories = new HashSet<>();
        }
    }


    public void addCategory(Category category) {
        if (category != null) {
            getCategories().add(category);
        }
    }

    public void removeCategory(Category category) {
        if (category != null) {
            getCategories().remove(category);
        }
    }

}