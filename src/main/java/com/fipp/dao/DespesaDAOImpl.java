package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Despesa;
import com.fipp.models.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class DespesaDAOImpl implements DAO<Despesa> {

    private static final Logger logger = LoggerFactory.getLogger(DespesaDAOImpl.class);
    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public ArrayList<Despesa> getAll(){

        ArrayList<Despesa> despesas = new ArrayList<>();

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_DESPESA ORDER BY CD_DESPESA");
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var despesa = new Despesa
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
                despesas.add(despesa);
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

        return despesas;
    }


    @Override
    public Despesa getById(int id){

        Despesa despesa = null;

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_DESPESA WHERE CD_DESPESA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                despesa = new Despesa
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

        return despesa;
    }


    @Override
    public int inserir(Despesa despesa){

        int response = 0;

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("INSERT INTO T_FPP_DESPESA" +
                    "(CD_DESPESA, CD_USUARIO, DT_DATA, VL_VALOR, NR_METODO, DS_DESCRICAO, CD_CATEGORIA, CD_SUBCATEGORIA, ST_STATUS, NM_BENEFICIARIO)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, despesa.getId());
            pstmt.setInt(2, despesa.getIdUsuario());
            pstmt.setDate(3, despesa.getData());
            pstmt.setBigDecimal(4, despesa.getValor());
            pstmt.setInt(5, despesa.getMetodo().getId());
            pstmt.setString(6, despesa.getDescricao());
            pstmt.setInt(7, despesa.getCategoria().getId());
            if(despesa.getSubcategoria() != null)
                pstmt.setInt(8, despesa.getSubcategoria().getId());
            else
                pstmt.setNull(8, Types.NULL);

            pstmt.setInt(9, despesa.getStatus().getId());
            pstmt.setString(10, despesa.getBeneficiario());

            response = pstmt.executeUpdate();
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

        return response;
    }

    @Override
    public boolean update(Despesa despesa){
        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("UPDATE T_FPP_DESPESA CD_USUARIO = ?, DT_DATA = ?, VL_VALOR = ?, NR_METODO = ?, DS_DESCRICAO = ?, CD_CATEGORIA = ?, CD_SUBCATEGORIA = ?, ST_STATUS = ?, NM_BENEFICIARIO = ? WHERE CD_DESPESA = ?");
            pstmt.setInt(1, despesa.getIdUsuario());
            pstmt.setDate(2, despesa.getData());
            pstmt.setBigDecimal(3, despesa.getValor());
            pstmt.setInt(4, despesa.getMetodo().getId());
            pstmt.setString(5, despesa.getDescricao());
            pstmt.setInt(6, despesa.getCategoria().getId());
            if(despesa.getSubcategoria() != null)
                pstmt.setInt(7, despesa.getSubcategoria().getId());
            else
                pstmt.setNull(7, Types.NULL);

            pstmt.setInt(8, despesa.getStatus().getId());
            pstmt.setString(9, despesa.getBeneficiario());
            pstmt.setInt(10, despesa.getId());

            pstmt.executeUpdate();
            return true;
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

        return false;
    }

}