package com.fipp.dao;

import com.fipp.models.entities.Receita;

import java.util.List;

public interface ReceitaDAO {

    List<Receita> getAll();

    Receita getById(int id);

    int inserir(Receita receita);

}
