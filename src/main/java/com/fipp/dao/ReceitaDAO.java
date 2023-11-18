package com.fipp.dao;

import com.fipp.models.entities.Receita;
import java.util.ArrayList;

public interface ReceitaDAO {

    ArrayList<Receita> getAll();

    Receita getById(int id);

    int inserir(Receita receita);

    boolean update(Receita receita);

}
