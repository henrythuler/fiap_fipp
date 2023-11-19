package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Categoria;
import com.fipp.models.enums.*;
import java.sql.*;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoriaDAOImpl implements DAO<Categoria> {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaDAOImpl.class);
    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public ArrayList<Categoria> getAll(){

        ArrayList<Categoria> categorias = new ArrayList<>();

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_CATEGORIA ORDER BY CD_CATEGORIA");
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var categoria = new Categoria
                        (
                                result.getInt(1),
                                result.getInt(2),
                                Tipo.valueById(result.getInt(3)),
                                result.getString(4)
                        );
                categorias.add(categoria);
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

        return categorias;
    }


    @Override
    public Categoria getById(int id){

        Categoria categoria = null;

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_CATEGORIA WHERE CD_CATEGORIA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                categoria = new Categoria
                        (
                                result.getInt(1),
                                result.getInt(2),
                                Tipo.valueById(result.getInt(3)),
                                result.getString(4)
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

        return categoria;
    }


    @Override
    public boolean inserir(Categoria categoria){

        var existsInDataBase = getById(categoria.getId()) != null;

        if (!existsInDataBase) {
            try{
                conexao = ConnectionManager.getInstance().getConnection();

                pstmt = conexao.prepareStatement("INSERT INTO T_FPP_CATEGORIA" +
                        "(CD_CATEGORIA, CD_USUARIO, ID_TIPO, DS_DESCRICAO)" +
                        "VALUES (?, ?, ?, ?)");

                pstmt.setInt(1, categoria.getId());
                pstmt.setInt(2, categoria.getIdUsuario());
                pstmt.setInt(3, categoria.getTipo().getId());
                pstmt.setString(4, categoria.getDescricao());

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
    public boolean update(Categoria categoria){

        var existsInDataBase = new CategoriaDAOImpl().getById(categoria.getId()) != null;

        if (existsInDataBase) {
            try {
                conexao = ConnectionManager.getInstance().getConnection();

                pstmt = conexao.prepareStatement("UPDATE T_FPP_CATEGORIA cd_usuario = ?, id_tipo = ?, ds_descricao = ? WHERE cd_categoria = ?");

                pstmt.setInt(1, categoria.getIdUsuario());
                pstmt.setInt(2, categoria.getTipo().getId());
                pstmt.setString(3, categoria.getDescricao());
                pstmt.setInt(4, categoria.getId());

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