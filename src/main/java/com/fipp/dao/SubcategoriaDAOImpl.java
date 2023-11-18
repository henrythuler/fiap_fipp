package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Subcategoria;
import com.fipp.models.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubcategoriaDAOImpl implements SubcategoriaDAO {

    private static final Logger logger = LoggerFactory.getLogger(SubcategoriaDAOImpl.class);
    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public ArrayList<Subcategoria> getAll(){

        ArrayList<Subcategoria> subcategorias = new ArrayList<>();

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA ORDER BY CD_SUBCATEGORIA");
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var subcategoria = new Subcategoria
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getInt(3),
                                Tipo.valueById(result.getInt(4)),
                                result.getString(5)
                        );
                subcategorias.add(subcategoria);
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

        return subcategorias;
    }


    @Override
    public Subcategoria getById(int id){

        Subcategoria subcategoria = null;

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA WHERE CD_SUBCATEGORIA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();


            while(result.next()){
                subcategoria = new Subcategoria
                        (
                            result.getInt(1),
                            result.getInt(2),
                            result.getInt(3),
                            Tipo.valueById(result.getInt(4)),
                            result.getString(5)
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

        return subcategoria;
    }


    @Override
    public ArrayList<Subcategoria> getByCategoriaId(int id){

        ArrayList<Subcategoria> subcategorias = new ArrayList<>();

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA WHERE CD_CATEGORIA = ? ORDER BY CD_CATEGORIA");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();


            while(result.next()){
                var subcategoria = new Subcategoria
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getInt(3),
                                Tipo.valueById(result.getInt(4)),
                                result.getString(5)
                        );
                subcategorias.add(subcategoria);
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

        return subcategorias;
    }


    @Override
    public int inserir(Subcategoria subcategoria){

        int response = 0;

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("INSERT INTO T_FPP_SUBCATEGORIA" +
                    "(CD_SUBCATEGORIA, CD_CATEGORIA, CD_USUARIO, ID_TIPO, DS_DESCRICAO)" +
                    "VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, subcategoria.getId());
            pstmt.setInt(2, subcategoria.getCategoriaId());
            pstmt.setInt(3, subcategoria.getIdUsuario());
            pstmt.setInt(4, subcategoria.getTipo().getId());
            pstmt.setString(5, subcategoria.getDescricao());

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
    public boolean update(Subcategoria subcategoria){
        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("UPDATE T_FPP_SUBCATEGORIA SET cd_categoria = ?, cd_usuario = ?, id_tipo = ?, ds_descricao = ? WHERE cd_subcategoria = ?");
            pstmt.setInt(1, subcategoria.getCategoriaId());
            pstmt.setInt(2, subcategoria.getIdUsuario());
            pstmt.setInt(3, subcategoria.getTipo().getId());
            pstmt.setString(4, subcategoria.getDescricao());
            pstmt.setInt(5, subcategoria.getId());

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
