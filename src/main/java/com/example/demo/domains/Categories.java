package com.example.demo.domains;

public class Categories {
    private Long id;
    private String name;
    private String description;

    public Categories(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
