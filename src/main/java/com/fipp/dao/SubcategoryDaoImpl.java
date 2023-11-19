package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Subcategory;
import com.fipp.models.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;

public class SubcategoryDaoImpl implements SubcategoryDao {

    private static final Logger logger = LoggerFactory.getLogger(SubcategoryDaoImpl.class);
    private Connection connection;
    PreparedStatement pstmt = null;


    @Override
    public ArrayList<Subcategory> getAll(){

        ArrayList<Subcategory> subcategories = new ArrayList<>();

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA ORDER BY CD_SUBCATEGORIA");

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var subcategory = new Subcategory
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getInt(3),
                                Type.valueById(result.getInt(4)),
                                result.getString(5)
                        );
                subcategories.add(subcategory);
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

        return subcategories;
    }


    @Override
    public Subcategory getById(int id){

        Subcategory subcategory = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA WHERE CD_SUBCATEGORIA = ?");
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                subcategory = new Subcategory
                        (
                            result.getInt(1),
                            result.getInt(2),
                            result.getInt(3),
                            Type.valueById(result.getInt(4)),
                            result.getString(5)
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

        return subcategory;
    }


    @Override
    public ArrayList<Subcategory> getByCategoryId(int id){

        ArrayList<Subcategory> subcategories = new ArrayList<>();

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA WHERE CD_CATEGORIA = ? ORDER BY CD_CATEGORIA");
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var subcategory = new Subcategory
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getInt(3),
                                Type.valueById(result.getInt(4)),
                                result.getString(5)
                        );
                subcategories.add(subcategory);
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

        return subcategories;
    }


    @Override
    public boolean insert(Subcategory subcategory){
        var existsInDataBase = new SubcategoryDaoImpl().getById(subcategory.getId()) != null;

        if (!existsInDataBase) {
            try {
                connection = ConnectionManager.getInstance().getConnection();
                pstmt = connection.prepareStatement("INSERT INTO T_FPP_SUBCATEGORIA" +
                        "(CD_SUBCATEGORIA, CD_CATEGORIA, CD_USUARIO, ID_TIPO, DS_DESCRICAO)" +
                        "VALUES (?, ?, ?, ?, ?)");
                pstmt.setInt(1, subcategory.getId());
                pstmt.setInt(2, subcategory.getCategoryId());
                pstmt.setInt(3, subcategory.getUserId());
                pstmt.setInt(4, subcategory.getTipo().getId());
                pstmt.setString(5, subcategory.getDescription());

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
    public boolean update(Subcategory subcategory){
        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("UPDATE T_FPP_SUBCATEGORIA SET CD_CATEGORIA = ?, CD_USUARIO = ?, ID_TIPO = ?, DS_DESCRICAO = ? WHERE CD_SUBCATEGORIA = ?");
            pstmt.setInt(1, subcategory.getCategoryId());
            pstmt.setInt(2, subcategory.getUserId());
            pstmt.setInt(3, subcategory.getTipo().getId());
            pstmt.setString(4, subcategory.getDescription());
            pstmt.setInt(5, subcategory.getId());

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

        return false;
    }

}
