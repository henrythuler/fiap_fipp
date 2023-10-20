package dao;

import db.FippDBManager;
import models.entities.Categoria;
import models.entities.Subcategoria;
import models.enums.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubcategoriaDAO {

    static Connection conexao;


    public static Subcategoria getSubcategoriaById(int id)
    {

        conexao = FippDBManager.obterConexao();
        PreparedStatement pstmt = null;

        Subcategoria subcategoria = null;

        try{

            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_SUBCATEGORIA WHERE CD_SUBCATEGORIA = ?");
            pstmt.setInt(1, id);

            ResultSet result = pstmt.executeQuery();

            while(result.next()){

                subcategoria.setId(result.getInt(1));
                subcategoria.setIdUsuario(result.getInt(2));
                subcategoria.setTipo(Tipo.valueById(result.getInt(3)));
                subcategoria.setDescricao(result.getString(4));
                subcategoria.setCategoriaId(result.getInt(5));

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

            return subcategoria;

        }

    }

}
