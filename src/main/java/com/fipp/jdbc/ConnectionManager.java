package com.fipp.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
    private static ConnectionManager instance;

    private ConnectionManager(){}

    public static ConnectionManager getInstance(){
        if (instance == null)
            instance = new ConnectionManager();
        return instance;
    }

    public Connection getConnection()
    {
        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String connectionString = System.getenv("DB_CONNECTION_STRING");
            connection = DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            logger.error("Erro: ", e);
        }

        return connection;
    }
}
