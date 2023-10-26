package br.com.fipp.dao;

import br.com.fipp.models.entities.Categoria;
import java.util.List;

public interface CategoriaDAO {

    List<Categoria> getAll();

    Categoria getById(int id);

    int inserir(Categoria categoria);

}
