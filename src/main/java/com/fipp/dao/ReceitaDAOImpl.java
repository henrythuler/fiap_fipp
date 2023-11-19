package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Receita;
import com.fipp.models.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;

public class ReceitaDAOImpl implements DAO<Receita> {

    private static final Logger logger = LoggerFactory.getLogger(ReceitaDAOImpl.class);
    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public ArrayList<Receita> getAll(){

        ArrayList<Receita> receitas = new ArrayList<>();

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_RECEITA ORDER BY CD_RECEITA");

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var receita = new Receita
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getDate(3),
                                result.getBigDecimal(4),
                                Metodo.valueById(result.getInt(5)),
                                result.getString(6),
                                new CategoriaDAOImpl().getById(result.getInt(7)),
                                new SubcategoriaDAOImpl().getById(result.getInt(8)),
                                Status.valueById(result.getInt(9)),
                                result.getString(10)
                        );
                receitas.add(receita);
            }
        } catch (SQLException e) {
            logger.error("Erro de SQL: ", e);
        } finally {
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException e) {
                logger.error("Erro ao fechar recursos: ", e);
            }
        }

        return receitas;
    }


    @Override
    public Receita getById(int id){

        Receita receita = null;

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_RECEITA WHERE CD_RECEITA = ?");
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                receita = new Receita
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getDate(3),
                                result.getBigDecimal(4),
                                Metodo.valueById(result.getInt(5)),
                                result.getString(6),
                                new CategoriaDAOImpl().getById(result.getInt(7)),
                                new SubcategoriaDAOImpl().getById(result.getInt(8)),
                                Status.valueById(result.getInt(9)),
                                result.getString(10)
                        );
            }
        } catch (SQLException e) {
            logger.error("Erro de SQL: ", e);
        } finally {
            try {
                pstmt.close();
                conexao.close();
            } catch (SQLException e) {
                logger.error("Erro ao fechar recursos: ", e);
            }
        }

        return receita;
    }


    @Override
    public boolean inserir(Receita receita){

        var existsInDataBase = new ReceitaDAOImpl().getById(receita.getId()) != null;

        if (!existsInDataBase) {
            try {
                conexao = ConnectionManager.getInstance().getConnection();
                pstmt = conexao.prepareStatement("INSERT INTO T_FPP_RECEITA" +
                        "(CD_RECEITA, CD_USUARIO, DT_DATA, VL_VALOR, NR_METODO, DS_DESCRICAO, CD_CATEGORIA, CD_SUBCATEGORIA, ST_STATUS, NM_PAGADOR)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setInt(1, receita.getId());
                pstmt.setInt(2, receita.getIdUsuario());
                pstmt.setDate(3, receita.getData());
                pstmt.setBigDecimal(4, receita.getValor());
                pstmt.setInt(5, receita.getMetodo().getId());
                pstmt.setString(6, receita.getDescricao());
                pstmt.setInt(7, receita.getCategoria().getId());
                if (receita.getSubcategoria() != null)
                    pstmt.setInt(8, receita.getSubcategoria().getId());
                else
                    pstmt.setNull(8, Types.NULL);
                pstmt.setInt(9, receita.getStatus().getId());
                pstmt.setString(10, receita.getPagador());

                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                logger.error("Erro de SQL: ", e);
            } finally {
                try {
                    pstmt.close();
                    conexao.close();
                } catch (SQLException e) {
                    logger.error("Erro ao fechar recursos: ", e);
                }
            }
        }

        return false;
    }


    @Override
    public boolean update(Receita receita){

        var existsInDataBase = new ReceitaDAOImpl().getById(receita.getId()) != null;

        if (existsInDataBase) {
            try {
                conexao = ConnectionManager.getInstance().getConnection();
                pstmt = conexao.prepareStatement("UPDATE T_FPP_RECEITA CD_USUARIO = ?, DT_DATA = ?, VL_VALOR = ?, NR_METODO = ?, DS_DESCRICAO = ?, CD_CATEGORIA = ?, CD_SUBCATEGORIA = ?, ST_STATUS = ?, NM_PAGADOR = ? WHERE CD_RECEITA = ?");
                pstmt.setInt(1, receita.getIdUsuario());
                pstmt.setDate(2, receita.getData());
                pstmt.setBigDecimal(3, receita.getValor());
                pstmt.setInt(4, receita.getMetodo().getId());
                pstmt.setString(5, receita.getDescricao());
                pstmt.setInt(6, receita.getCategoria().getId());
                if (receita.getSubcategoria() != null)
                    pstmt.setInt(7, receita.getSubcategoria().getId());
                else
                    pstmt.setNull(7, Types.NULL);
                pstmt.setInt(8, receita.getStatus().getId());
                pstmt.setString(9, receita.getPagador());
                pstmt.setInt(10, receita.getId());

                return pstmt.executeUpdate() > 0;
            } catch (SQLException e) {
                logger.error("Erro de SQL: ", e);
            } finally {
                try {
                    pstmt.close();
                    conexao.close();
                } catch (SQLException e) {
                    logger.error("Erro ao fechar recursos: ", e);
                }
            }
        }

        return false;
    }

}