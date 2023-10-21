package dao;

import db.FippDBManager;
import models.entities.Categoria;
import models.entities.Receita;
import models.entities.Subcategoria;
import models.enums.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoriaDAO {

    static Connection conexao;


    public static Subcategoria getById(int id)
    {
        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;
        Subcategoria subcategoria = null;

        try
        {
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA WHERE CD_SUBCATEGORIA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();


            //Subcategoria(int id, int categoriaId, Tipo tipo, String descricao)

            while(result.next())
            {
                subcategoria = new Subcategoria
                        (
                            result.getInt(1),
                            result.getInt(2),
                            Tipo.valueById(result.getInt(4)),
                            result.getString(5)
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
        }
        return subcategoria;
    }



    public static List<Subcategoria> getByCategoriaId(int id)
    {
        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;
        List<Subcategoria> subcategorias = new ArrayList<>();

        try
        {
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA WHERE CD_CATEGORIA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            //(int id, int categoriaId, Tipo tipo, String descricao)

            while(result.next())
            {
                var subcategoria = new Subcategoria
                        (
                                result.getInt(1),
                                result.getInt(2),
                                Tipo.valueById(result.getInt(4)),
                                result.getString(5)
                        );
                subcategorias.add(subcategoria);
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
        }
        return subcategorias;
    }



    public static void inserir()
    {
        // TO DO: Implementar
    }



    public static void excluir()
    {
        // TO DO: Implementar
    }


}
