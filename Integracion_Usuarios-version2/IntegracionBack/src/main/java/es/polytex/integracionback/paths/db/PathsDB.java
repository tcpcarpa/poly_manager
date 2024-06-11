package es.polytex.integracionback.paths.db;

import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;

import java.sql.*;

public class PathsDB extends DB {
    public String getPathLimitGrup(String siteId) throws SQLException {
        connect();
        try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_PATH_LIM)) {
            statement.setString(1, siteId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("path_limitGrup");
                }
            }
            disconnect();
            return null;
        }
    }

    public String getPathUsuarios(String siteId) throws SQLException {
        connect();
        try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_PATH_USU)) {
            statement.setString(1, siteId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("path_limitGrup");
                }
            }
            disconnect();
            return null;
        }
    }
}
