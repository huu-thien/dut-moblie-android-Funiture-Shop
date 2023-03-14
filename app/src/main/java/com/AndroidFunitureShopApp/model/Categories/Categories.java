package com.AndroidFunitureShopApp.model.Categories;

public class Categories {
    private int id;
    private int idCategories;
    private String name;
    private String title;
    private String description;
    private String imageUrl;

    public Categories(int id, int idCategories, String name, String title, String description, String imageUrl) {
        this.id = id;
        this.idCategories = idCategories;
        this.name = name;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
