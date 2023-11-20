package com.fipp.models.entities;

import com.fipp.models.enums.*;

public class Subcategory
{
    private int id;
    private final int categoryId;
    private final int userId;
    private final String description;
    private final Type type;


    public Subcategory(int id, int categoryId, int userId, Type type, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.userId = userId;
        this.type = type;
        this.description = description;
    }


    public String getDescription()
    {
        return this.description;
    }
    public int getId() { return id; }
    public int getCategoryId() { return categoryId; }
    public Type getType() { return type; }
    public int getUserId() { return userId; }
    public void setId(int id) { this.id = id; }
}
