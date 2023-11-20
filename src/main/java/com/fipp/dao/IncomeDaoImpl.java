package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Income;
import com.fipp.models.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;

public class IncomeDaoImpl implements Dao<Income> {

    private static final Logger logger = LoggerFactory.getLogger(IncomeDaoImpl.class);
    private Connection connection;

    @Override
    public ArrayList<Income> getAll(){

        ArrayList<Income> incomes = new ArrayList<>();
        PreparedStatement pstmt = null;

        try{
            System.out.println(connection == null);
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_RECEITA ORDER BY CD_RECEITA");

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var income = new Income
                        (
                                result.getInt(2),
                                result.getDate(3),
                                result.getBigDecimal(4),
                                Method.valueById(result.getInt(5)),
                                result.getString(6),
                                new CategoryDaoImpl().getById(result.getInt(7)),
                                new SubcategoryDaoImpl().getById(result.getInt(8)),
                                Status.valueById(result.getInt(9)),
                                result.getString(10)
                        );
                incomes.add(income);
            }
        } catch (SQLException e) {
            logger.error("Erro de SQL: ", e);
        } finally {
            try {
                assert pstmt != null;
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Erro ao fechar recursos: ", e);
            }
        }

        return incomes;
    }


    @Override
    public Income getById(int id){

        Income income = null;
        PreparedStatement pstmt = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_RECEITA WHERE CD_RECEITA = ?");
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                income = new Income
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getDate(3),
                                result.getBigDecimal(4),
                                Method.valueById(result.getInt(5)),
                                result.getString(6),
                                new CategoryDaoImpl().getById(result.getInt(7)),
                                new SubcategoryDaoImpl().getById(result.getInt(8)),
                                Status.valueById(result.getInt(9)),
                                result.getString(10)
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

        return income;
    }


    @Override
    public boolean insert(Income income){

        var existsInDataBase = new IncomeDaoImpl().getById(income.getId()) != null;
        PreparedStatement pstmt = null;

        if (!existsInDataBase) {
            try {
                connection = ConnectionManager.getInstance().getConnection();
                pstmt = connection.prepareStatement("INSERT INTO T_FPP_RECEITA" +
                        "(CD_RECEITA, CD_USUARIO, DT_DATA, VL_VALOR, NR_METODO, DS_DESCRICAO, CD_CATEGORIA, CD_SUBCATEGORIA, ST_STATUS, NM_PAGADOR)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setInt(1, income.getId());
                pstmt.setInt(2, income.getUserId());
                pstmt.setDate(3, income.getDate());
                pstmt.setBigDecimal(4, income.getValue());
                pstmt.setInt(5, income.getMetodo().getId());
                pstmt.setString(6, income.getDescription());
                pstmt.setInt(7, income.getCategoria().getId());
                if (income.getSubcategoria() != null)
                    pstmt.setInt(8, income.getSubcategoria().getId());
                else
                    pstmt.setNull(8, Types.NULL);
                pstmt.setInt(9, income.getStatus().getId());
                pstmt.setString(10, income.getPayer());

                pstmt.executeUpdate();

                System.out.println("Cadastro inserido com sucesso!");

                return true;

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
    public boolean update(Income income){

        var existsInDataBase = new IncomeDaoImpl().getById(income.getId()) != null;
        PreparedStatement pstmt = null;

        if (existsInDataBase) {
            try {
                connection = ConnectionManager.getInstance().getConnection();
                pstmt = connection.prepareStatement("UPDATE T_FPP_RECEITA CD_USUARIO = ?, DT_DATA = ?, VL_VALOR = ?, NR_METODO = ?, DS_DESCRICAO = ?, CD_CATEGORIA = ?, CD_SUBCATEGORIA = ?, ST_STATUS = ?, NM_PAGADOR = ? WHERE CD_RECEITA = ?");
                pstmt.setInt(1, income.getUserId());
                pstmt.setDate(2, income.getDate());
                pstmt.setBigDecimal(3, income.getValue());
                pstmt.setInt(4, income.getMetodo().getId());
                pstmt.setString(5, income.getDescription());
                pstmt.setInt(6, income.getCategoria().getId());
                if (income.getSubcategoria() != null)
                    pstmt.setInt(7, income.getSubcategoria().getId());
                else
                    pstmt.setNull(7, Types.NULL);
                pstmt.setInt(8, income.getStatus().getId());
                pstmt.setString(9, income.getPayer());
                pstmt.setInt(10, income.getId());

                pstmt.executeUpdate();

                System.out.println("Cadastro atualizado com sucesso!");

                return true;

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