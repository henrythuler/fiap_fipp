package com.fipp.models.enums;

public enum Type {
    EXPENSE(0, "Despesa"),
    INCOME(1, "Receita");


    private final int id;
    private final String description;


    Type(int id, String description)
    {
        this.id = id;
        this.description = description;
    }


    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }


    public static Type valueById(int id) {
        for(Type t : Type.values()){
            if(t.getId() == id) return t;
        }
        return null;
    }

}