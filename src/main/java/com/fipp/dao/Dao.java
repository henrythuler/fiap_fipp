package com.fipp.dao;

import java.util.ArrayList;

public interface Dao<T>{

    ArrayList<T> getAll();

    T getById(int id);

    boolean insert(T object);

    boolean update(T object);

}
