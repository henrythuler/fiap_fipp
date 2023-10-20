package dao;

import db.FippDBManager;
import models.entities.Receita;
import models.enums.Metodo;
import models.enums.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    static Connection conexao;

    public static void inserir(Receita receita)
    {
        //Recuperando uma conexão com o Banco de Dados
        conexao = FippDBManager.obterConexao();

        //Instanciando o meu statement
        PreparedStatement pstmt = null;

        try{

            pstmt = conexao.prepareStatement("INSERT INTO T_FPP_RECEITA" +
                    "(CD_RECEITA, CD_USUARIO, DT_DATA, VL_VALOR, NR_METODO, NM_PAGADOR, DS_RECEITA, ST_STATUS, CD_CATEGORIA, CD_SUBCATEGORIA)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            //Passando os valores para cada coluna
            pstmt.setInt(1, receita.getId());
            pstmt.setInt(2, receita.getIdUsuario());
            pstmt.setDate(3, receita.getData());
            pstmt.setBigDecimal(4, receita.getValor());
            pstmt.setInt(5, receita.getMetodo().getId());
            pstmt.setString(6, receita.getPagador());
            pstmt.setString(7, receita.getDescricao());
            pstmt.setInt(8, receita.getStatus().getId());
            pstmt.setInt(9, receita.getCategoria().getId());

            if(receita.getSubcategoria() != null) pstmt.setInt(10, receita.getSubcategoria().getId());
            else pstmt.setNull(10, Types.NULL);

            pstmt.executeUpdate();

            System.out.println("Receita cadastrada com sucesso!");

        }catch(SQLException e){

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

    }


    public static List<Receita> getAll()
    {

        //Recuperando uma conexão com o Banco de Dados
        conexao = FippDBManager.obterConexao();

        //Instanciando o meu statement
        PreparedStatement pstmt = null;

        List<Receita> receitas = new ArrayList<>();

        try{

            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_RECEITA");
            ResultSet result = pstmt.executeQuery();

            while(result.next()){

                //Criando um objeto de receita para armazenar a receita recuperada do Banco de Dados
                Receita receita = new Receita();

                receita.setId(result.getInt(1));
                receita.setIdUsuario(result.getInt(2));
                receita.setData(result.getDate(3));
                receita.setValor(result.getBigDecimal(4));
                receita.setMetodo(Metodo.valueById(result.getInt(5)));
                receita.setPagador(result.getString(6));
                receita.setDescricao(result.getString(7));
                receita.setStatus(Status.valueById(8));
                receita.setCategoria(CategoriaDAO.getCategoriaById(result.getInt(9)));

                receitas.add(receita);

            }

        }catch (SQLException e){

            System.err.println(e.getMessage());
            e.printStackTrace();

        }finally {

            try{

                pstmt.close();
                conexao.close();

            }catch (SQLException e){

                System.err.println(e.getMessage());
                e.printStackTrace();

            }

            return receitas;

        }

    }

}
