package br.com.fipp.dao;

import br.com.fipp.jdbc.FippDBManager;
import br.com.fipp.models.entities.Receita;
import br.com.fipp.models.enums.Metodo;
import br.com.fipp.models.enums.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAOImpl implements ReceitaDAO {

    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public List<Receita> getAll(){

        List<Receita> receitas = new ArrayList<>();

        try{
            conexao = FippDBManager.obterConexao();
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

        return receitas;
    }


    @Override
    public Receita getById(int id){

        Receita receita = null;

        try{
            conexao = FippDBManager.obterConexao();
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

        return receita;
    }


    @Override
    public int inserir(Receita receita){

        int response = 0;

        try{
            conexao = FippDBManager.obterConexao();
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
            if(receita.getSubcategoria() != null)
                pstmt.setInt(8, receita.getSubcategoria().getId());
            else
                pstmt.setNull(8, Types.NULL);

            pstmt.setInt(9, receita.getStatus().getId());
            pstmt.setString(10, receita.getPagador());

            response = pstmt.executeUpdate();

        }catch(SQLException e){
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