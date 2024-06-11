package es.polytex.integracionback.client.db;

import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDB extends DB {
    public List<String[]> getlimit(String siteID) throws SQLException {
        connect();
        List<String[]> listalimit = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SELECT_LIMIT_ALL)) {
            preparedStatement.setString(1, siteID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String[] row = new String[4];
                    row[0] = resultSet.getString("DepartmentName");
                    row[1] = resultSet.getString("title");
                    row[2] = resultSet.getString("effectiveLimitGroup");
                    row[3] = resultSet.getString("site_id");
                    listalimit.add(row);
                }
            }
            disconnect();
            return listalimit;
        }
    }



}


