package dao;

import db.FippDBManager;
import models.entities.Categoria;
import models.entities.Receita;
import models.enums.Tipo;

import java.sql.*;

public class CategoriaDAO {

    static Connection conexao;


    public static void inserir(Categoria categoria)
    {
        //Recuperando uma conexão com o Banco de Dados
        conexao = FippDBManager.obterConexao();

        //Instanciando o meu statement
        PreparedStatement pstmt = null;

        try{

            pstmt = conexao.prepareStatement("INSERT INTO T_FPP_CATEGORIA" +
                    "(ID_TIPO, DS_CATEGORIA, CD_USUARIO, CD_CATEGORIA)" +
                    "VALUES (?, ?, ?, ?)");

            //Passando os valores para cada coluna
            pstmt.setInt(1, categoria.getTipo().getId());
            pstmt.setString(2, categoria.getDescricao());
            pstmt.setInt(3, categoria.getIdUsuario());
            pstmt.setInt(4, categoria.getId());

            pstmt.executeUpdate();

            System.out.println("Categoria cadastrada com sucesso!");

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


    public static Categoria getCategoriaById(int id)
    {

        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;

        Categoria categoria = null;

        try{

            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_CATEGORIA WHERE CD_CATEGORIA = ?");
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();

            while(result.next()){

                categoria.setTipo(Tipo.valueById(result.getInt(1)));
                categoria.setDescricao(result.getString(2));
                categoria.setIdUsuario(result.getInt(3));
                categoria.setId(result.getInt(4));

            }

        }catch (SQLException e){

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

            return categoria;

        }

    }

}
