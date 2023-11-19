package com.fipp.dao;

import com.fipp.models.entities.Subcategoria;
import java.util.ArrayList;

public interface SubcategoriaDAO extends DAO<Subcategoria>{

    ArrayList<Subcategoria> getByCategoriaId(int id);

}
