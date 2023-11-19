package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Category;
import com.fipp.models.enums.*;
import java.sql.*;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryDaoImpl implements Dao<Category> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private Connection connection;
    PreparedStatement pstmt = null;


    @Override
    public ArrayList<Category> getAll(){

        ArrayList<Category> categories = new ArrayList<>();

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_CATEGORIA ORDER BY CD_CATEGORIA");
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var category = new Category
                        (
                                result.getInt(1),
                                result.getInt(2),
                                Type.valueById(result.getInt(3)),
                                result.getString(4)
                        );
                categories.add(category);
            }
        } catch (SQLException e) {
            logger.error("Erro de SQL: ", e);
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Erro ao fechar recursos: ", e);
            }
        }

        return categories;
    }


    @Override
    public Category getById(int id){

        Category category = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_CATEGORIA WHERE CD_CATEGORIA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                category = new Category
                        (
                                result.getInt(1),
                                result.getInt(2),
                                Type.valueById(result.getInt(3)),
                                result.getString(4)
                        );
            }

        } catch (SQLException e) {
            logger.error("Erro de SQL: ", e);
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Erro ao fechar recursos: ", e);
            }
        }

        return category;
    }


    @Override
    public boolean insert(Category category){

        var existsInDataBase = getById(category.getId()) != null;

        if (!existsInDataBase) {
            try{
                connection = ConnectionManager.getInstance().getConnection();

                pstmt = connection.prepareStatement("INSERT INTO T_FPP_CATEGORIA" +
                        "(CD_CATEGORIA, CD_USUARIO, ID_TIPO, DS_DESCRICAO)" +
                        "VALUES (?, ?, ?, ?)");

                pstmt.setInt(1, category.getId());
                pstmt.setInt(2, category.getUserId());
                pstmt.setInt(3, category.getType().getId());
                pstmt.setString(4, category.getDescription());

                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                logger.error("Erro de SQL: ", e);
            } finally {
                try {
                    pstmt.close();
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Erro ao fechar recursos: ", e);
                }
            }
        }

        return false;
    }


    @Override
    public boolean update(Category category){

        var existsInDataBase = new CategoryDaoImpl().getById(category.getId()) != null;

        if (existsInDataBase) {
            try {
                connection = ConnectionManager.getInstance().getConnection();

                pstmt = connection.prepareStatement("UPDATE T_FPP_CATEGORIA CD_USUARIO = ?, ID_TIPO = ?, DS_DESCRICAO = ? WHERE CD_CATEGORIA = ?");

                pstmt.setInt(1, category.getUserId());
                pstmt.setInt(2, category.getType().getId());
                pstmt.setString(3, category.getDescription());
                pstmt.setInt(4, category.getId());

                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                logger.error("Erro de SQL: ", e);
            } finally {
                try {
                    pstmt.close();
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Erro ao fechar recursos: ", e);
                }
            }
        }

        return false;
    }

}