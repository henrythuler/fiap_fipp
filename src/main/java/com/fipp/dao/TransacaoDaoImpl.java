package com.fipp.dao;

import com.fipp.jdbc.ConnectionManager;
import com.fipp.models.entities.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransacaoDaoImpl implements TransactionDao{

    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private Connection connection;
    PreparedStatement pstmt = null;

    @Override
    public ArrayList<TransactionRecord> getAll() {

        ArrayList<TransactionRecord> transactions = new ArrayList<>();
        PreparedStatement pstmt = null;

        try{
            System.out.println(connection == null);
            connection = ConnectionManager.getInstance().getConnection();
            pstmt = connection.prepareStatement("SELECT CD_RECEITA AS \"CODIGO\", DT_DATA AS \"Data\", CD_CATEGORIA AS \"Categoria\", VL_VALOR AS \"Valor\" FROM T_FPP_RECEITA WHERE CD_USUARIO = 1 UNION ALL SELECT CD_DESPESA AS \"CODIGO\", DT_DATA, CD_CATEGORIA, VL_VALOR * -1 FROM T_FPP_DESPESA WHERE CD_USUARIO = 1");

            ResultSet result = pstmt.executeQuery();

            while(result.next()){
                var transaction = new TransactionRecord
                        (
                                result.getInt(1),
                                result.getDate(2),
                                result.getBigDecimal(4),
                                new CategoryDaoImpl().getById(result.getInt(3)).getDescription()
                        );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            logger.error("Erro de SQL: ", e);
        } finally {
            try {
                assert pstmt != null;
                pstmt.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Erro ao fechar recursos: ", e);
            }
        }

        return transactions;

    }

}
