package es.polytex.integracionback.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    protected static final String SCHEMA_NAME = "polytex_db";
    protected static final String USER_CONNECTION = "root";
    protected static final String PASS_CONNECTION = "tcp";
    protected static final String CONNECTION = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME +
            "?enabledTLSProtocols=TLSv1.2&useSSL=false&allowPublicKeyRetrieval=true";

    protected Connection connection;

    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION, USER_CONNECTION, PASS_CONNECTION);
        } catch (ClassNotFoundException e) {
            connection = null;
        }
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
