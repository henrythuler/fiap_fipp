package com.fipp.dao;


import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.Categoria;
import com.fipp.models.enums.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public List<Categoria> getAll(){

        List<Categoria> categorias = new ArrayList<>();

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
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            try{
                pstmt.close();
                conexao.close();
            }catch (SQLException e){
                System.err.println(e.getMessage());
                e.printStackTrace();
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

        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
                pstmt.close();
                conexao.close();
            }catch (SQLException e){
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        return categoria;
    }


    @Override
    public int inserir(Categoria categoria){

        int response = 0;

        try{
            conexao = ConnectionManager.getInstance().getConnection();
            pstmt = conexao.prepareStatement("INSERT INTO T_FPP_CATEGORIA" +
                    "(CD_CATEGORIA, CD_USUARIO, ID_TIPO, DS_DESCRICAO)" +
                    "VALUES (?, ?, ?, ?)");

            pstmt.setInt(1, categoria.getId());
            pstmt.setInt(2, categoria.getIdUsuario());
            pstmt.setInt(3, categoria.getTipo().getId());
            pstmt.setString(4, categoria.getDescricao());

            response = pstmt.executeUpdate();
        }catch(SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conexao.close();
            }catch (SQLException e){
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        return response;
    }

}