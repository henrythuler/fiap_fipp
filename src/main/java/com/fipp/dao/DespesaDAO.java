package com.fipp.dao;

import com.fipp.models.entities.Despesa;

import java.util.ArrayList;
import java.util.List;

public interface DespesaDAO {

    ArrayList<Despesa> getAll();

    Despesa getById(int id);

    int inserir(Despesa despesa);

    boolean update(Despesa despesa);
}
