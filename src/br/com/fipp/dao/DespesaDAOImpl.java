package br.com.fipp.dao;

import br.com.fipp.jdbc.FippDBManager;
import br.com.fipp.models.entities.Despesa;
import br.com.fipp.models.enums.Metodo;
import br.com.fipp.models.enums.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAOImpl implements DespesaDAO {

    private Connection conexao;
    PreparedStatement pstmt = null;


    @Override
    public List<Despesa> getAll(){

        List<Despesa> despesas = new ArrayList<>();

        try{
            conexao = FippDBManager.obterConexao();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_DESPESA ORDER BY CD_DESPESA");
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var despesa = new Despesa
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
                despesas.add(despesa);
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

        return despesas;
    }


    @Override
    public Despesa getById(int id){

        Despesa despesa = null;

        try{
            conexao = FippDBManager.obterConexao();
            pstmt = conexao.prepareStatement("SELECT * FROM T_FPP_DESPESA WHERE CD_DESPESA = ?");
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                despesa = new Despesa
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

        return despesa;
    }


    @Override
    public int inserir(Despesa despesa){

        int response = 0;

        try{
            conexao = FippDBManager.obterConexao();
            pstmt = conexao.prepareStatement("INSERT INTO T_FPP_DESPESA" +
                    "(CD_DESPESA, CD_USUARIO, DT_DATA, VL_VALOR, NR_METODO, DS_DESCRICAO, CD_CATEGORIA, CD_SUBCATEGORIA, ST_STATUS, NM_BENEFICIARIO)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, despesa.getId());
            pstmt.setInt(2, despesa.getIdUsuario());
            pstmt.setDate(3, despesa.getData());
            pstmt.setBigDecimal(4, despesa.getValor());
            pstmt.setInt(5, despesa.getMetodo().getId());
            pstmt.setString(6, despesa.getDescricao());
            pstmt.setInt(7, despesa.getCategoria().getId());
            if(despesa.getSubcategoria() != null)
                pstmt.setInt(8, despesa.getSubcategoria().getId());
            else
                pstmt.setNull(8, Types.NULL);

            pstmt.setInt(9, despesa.getStatus().getId());
            pstmt.setString(10, despesa.getBeneficiario());

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