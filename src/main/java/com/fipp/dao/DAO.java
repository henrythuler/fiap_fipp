package com.fipp.dao;

import java.util.ArrayList;

public interface DAO<T>{

    ArrayList<T> getAll();

    T getById(int id);

    int inserir(T object);

    boolean update(T categoria);

}
