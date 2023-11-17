package com.fipp.dao;

import com.fipp.models.entities.Despesa;
import java.util.List;

public interface DespesaDAO {

    List<Despesa> getAll();

    Despesa getById(int id);

    int inserir(Despesa despesa);
}
