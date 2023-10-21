package dao;

import db.FippDBManager;
import models.entities.Categoria;
import models.entities.Receita;
import models.enums.Tipo;

import java.sql.*;

public class CategoriaDAO {

    static Connection conexao;

    public static Categoria getById(int id)
    {
        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;
        Categoria categoria = null;

        try
        {
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_CATEGORIA WHERE CD_CATEGORIA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            while(result.next())
            {
                categoria = new Categoria
                        (
                                result.getInt(1),
                                result.getInt(2),
                                Tipo.valueById(result.getInt(3)),
                                result.getString(4)
                        );
            }

        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                conexao.close();
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }

            return categoria;
        }
    }



    public static int inserir(Categoria categoria)
    {
        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;
        int response = 0;

        try
        {
            pstmt = conexao.prepareStatement("INSERT INTO T_FPP_CATEGORIA" +
                    "(CD_CATEGORIA, CD_USUARIO, ID_TIPO, DS_DESCRICAO)" +
                    "VALUES (?, ?, ?, ?)");

            pstmt.setInt(1, categoria.getId());
            pstmt.setInt(2, categoria.getIdUsuario());
            pstmt.setInt(3, categoria.getTipo().getId());
            pstmt.setString(4, categoria.getDescricao());

            response = pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pstmt.close();
                conexao.close();
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        return response;
    }



    public static void excluir()
    {
        // TO DO: Implementar
    }

}
