package dao;

import db.FippDBManager;
import models.entities.Categoria;
import models.entities.Receita;
import models.enums.Metodo;
import models.enums.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    static Connection conexao;

    public static List<Receita> getAll()
    {
        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;
        List<Receita> receitas = new ArrayList<>();

        try
        {
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_RECEITA");
            ResultSet result = pstmt.executeQuery();

            while(result.next())
            {
                var receita = new Receita
                        (
                                result.getInt(1),
                                result.getInt(2),
                                result.getDate(3),
                                result.getBigDecimal(4),
                                Metodo.valueById(result.getInt(5)),
                                result.getString(6),
                                CategoriaDAO.getById(result.getInt(7)),
                                SubcategoriaDAO.getById(result.getInt(8)),
                                Status.valueById(9),
                                result.getString(10)
                        );
                receitas.add(receita);
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

            return receitas;
        }
    }

    public static Receita getById(int id)
    {
        //TO DO: Implementar
        return null;
    }

    public static int inserir(Receita receita)
    {
        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;
        int response = 0;

        try
        {
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


    public static int excluir(int id)
    {
        //TO DO: Implementar
        return 0;
    }
}
