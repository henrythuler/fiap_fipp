package com.fipp.dao;

import com.fipp.models.entities.Subcategory;
import java.util.ArrayList;

public interface SubcategoryDao extends Dao<Subcategory> {

    ArrayList<Subcategory> getByCategoryId(int id);

}
