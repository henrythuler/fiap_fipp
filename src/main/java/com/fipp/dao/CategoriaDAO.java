package com.fipp.dao;

import com.fipp.models.entities.Categoria;

import java.util.ArrayList;
import java.util.List;

public interface CategoriaDAO {

    ArrayList<Categoria> getAll();

    Categoria getById(int id);

    int inserir(Categoria categoria);
    boolean update(Categoria categoria);

}
