package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Expense;
import com.fipp.models.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;

public class ExpenseDaoImpl implements Dao<Expense> {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseDaoImpl.class);
    private Connection connection;
    PreparedStatement pstmt = null;


    @Override
    public ArrayList<Expense> getAll(){

        ArrayList<Expense> expenses = new ArrayList<>();

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_DESPESA ORDER BY CD_DESPESA");

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var expense = new Expense
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
                expenses.add(expense);
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

        return expenses;
    }


    @Override
    public Expense getById(int id){

        Expense expense = null;

        try{
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM T_FPP_DESPESA WHERE CD_DESPESA = ?");
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                expense = new Expense
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

        return expense;
    }


    @Override
    public boolean insert(Expense expense){

        var existsInDataBase = new ExpenseDaoImpl().getById(expense.getId()) != null;

        if (!existsInDataBase) {
            try {
                connection = ConnectionManager.getInstance().getConnection();
                //Na minha tabela não está DS_DESCRICAO, mas sim DS_DESPESA
                pstmt = connection.prepareStatement("INSERT INTO T_FPP_DESPESA" +
                        "(CD_DESPESA, CD_USUARIO, DT_DATA, VL_VALOR, NR_METODO, DS_DESPESA, CD_CATEGORIA, CD_SUBCATEGORIA, ST_STATUS, NM_BENEFICIARIO)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setInt(1, expense.getId());
                pstmt.setInt(2, expense.getUserId());
                pstmt.setDate(3, expense.getDate());
                pstmt.setBigDecimal(4, expense.getValue());
                pstmt.setInt(5, expense.getMetodo().getId());
                pstmt.setString(6, expense.getDescription());
                pstmt.setInt(7, expense.getCategoria().getId());
                if (expense.getSubcategoria() != null)
                    pstmt.setInt(8, expense.getSubcategoria().getId());
                else
                    pstmt.setNull(8, Types.NULL);
                pstmt.setInt(9, expense.getStatus().getId());
                pstmt.setString(10, expense.getBeneficiary());

                System.out.println("Cadastro inserido com sucesso!");

                pstmt.executeUpdate();

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
    public boolean update(Expense expense){

        var existsInDataBase = new ExpenseDaoImpl().getById(expense.getId()) != null;

        System.out.println("Entrei aqui no update!");

        if (existsInDataBase) {
            try {
                connection = ConnectionManager.getInstance().getConnection();
                //Na minha tabela não está DS_DESCRICAO, mas sim DS_DESPESA
                pstmt = connection.prepareStatement("UPDATE T_FPP_DESPESA CD_USUARIO = ?, DT_DATA = ?, VL_VALOR = ?, NR_METODO = ?, DS_DESPESA = ?, CD_CATEGORIA = ?, CD_SUBCATEGORIA = ?, ST_STATUS = ?, NM_BENEFICIARIO = ? WHERE CD_DESPESA = ?");
                pstmt.setInt(1, expense.getUserId());
                pstmt.setDate(2, expense.getDate());
                pstmt.setBigDecimal(3, expense.getValue());
                pstmt.setInt(4, expense.getMetodo().getId());
                pstmt.setString(5, expense.getDescription());
                pstmt.setInt(6, expense.getCategoria().getId());
                if (expense.getSubcategoria() != null)
                    pstmt.setInt(7, expense.getSubcategoria().getId());
                else
                    pstmt.setNull(7, Types.NULL);
                pstmt.setInt(8, expense.getStatus().getId());
                pstmt.setString(9, expense.getBeneficiary());
                pstmt.setInt(10, expense.getId());

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