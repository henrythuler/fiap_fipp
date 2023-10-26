package br.com.fipp.dao;

import br.com.fipp.jdbc.ConnectionManager;
import br.com.fipp.models.entities.Subcategoria;
import br.com.fipp.models.enums.Tipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoriaDAOImpl implements SubcategoriaDAO {

    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public List<Subcategoria> getAll(){

        List<Subcategoria> subcategorias = new ArrayList<>();

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

            return subcategorias;
        }
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

        return subcategoria;
    }


    @Override
    public List<Subcategoria> getByCategoriaId(int id){

        List<Subcategoria> subcategorias = new ArrayList<>();

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
        }catch(SQLException e) {
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

        return response;
    }

}
