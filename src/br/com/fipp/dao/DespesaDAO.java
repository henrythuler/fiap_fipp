package br.com.fipp.dao;

import br.com.fipp.models.entities.Despesa;
import java.util.List;

public interface DespesaDAO {

    List<Despesa> getAll();

    Despesa getById(int id);

    int inserir(Despesa despesa);
}
