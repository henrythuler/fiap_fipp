package com.fipp.models.entities;

import com.fipp.models.enums.*;

public class Category
{
    private int id;
    private final Type type;
    private String description;
    private final int userId;


    public Category(int id, int userId, Type type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.userId = userId;
    }


    public int getId() { return id; }
    public String getDescription() { return this.description; }
    public int getUserId() { return userId; }
    public Type getType() { return type; }
    public void setId(int id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
}
