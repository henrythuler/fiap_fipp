package com.fipp.dao;

import java.util.ArrayList;

public interface DAO<T>{

    ArrayList<T> getAll();

    T getById(int id);

    boolean inserir(T object);

    boolean update(T object);

}
