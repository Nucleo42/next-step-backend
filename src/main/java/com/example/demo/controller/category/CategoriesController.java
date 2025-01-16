package com.example.demo.controller.category;

import com.example.demo.domains.Categories;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private Map<Long, Categories> categoriesMap = new HashMap<>();
    private Long currentId = 1l;

    @GetMapping("/{id}")
    public Categories getCategory(@PathVariable Long id) {
        return categoriesMap.get(id);
    }

    @PostMapping
    public String createCategory(@RequestBody Categories category) {
        category.setId(currentId++);
        categoriesMap.put(category.getId(), category);
        return String.format("%s(%d) %s Category created successfully!", category.getName(), category.getId(), category.getDescription());
    }

    @GetMapping
    public Collection<Categories> getAllCategories() {
        return categoriesMap.values();
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        if (categoriesMap.containsKey(id)) {
            categoriesMap.remove(id);
            return "Category deleted successfully!";
        } else {
            return "Category not found!";
        }
    }
}
