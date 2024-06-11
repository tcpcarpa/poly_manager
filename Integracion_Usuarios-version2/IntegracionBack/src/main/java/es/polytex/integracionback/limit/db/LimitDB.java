package es.polytex.integracionback.limit.db;

import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LimitDB extends DB {
    public boolean insertarGrupLimit(List<String[]> limitList, String siteID) throws SQLException {
        connect();
        try (PreparedStatement statement = connection.prepareStatement(Query.INSERT_LIMIT)) {
            for (String[] limit : limitList) {
                statement.setString(1, limit[0]);
                statement.setString(2, limit[1]);
                statement.setString(3, limit[2]);
                statement.setString(4, siteID);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    return false;
                }
            }
            return true;
        } finally {
            disconnect();
        }
    }

    public boolean updateGrupLimit(List<String[]> limitList, String siteID) throws SQLException {
        connect();
        try (PreparedStatement statement = connection.prepareStatement(Query.UPDATE_LIMIT)) {
            for (String[] limit : limitList) {
                statement.setString(1, limit[2]);
                statement.setString(2, limit[0]);
                statement.setString(3, limit[1]);
                statement.setString(4, siteID);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected <= 0) {
                    return false;
                }
            }
            return true;
        } finally {
            disconnect();
        }
    }


    public List<String[]> selectLimitList(String siteID) throws SQLException {
        connect();
        List<String[]> limitList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_LIMIT_ALL)) {
            statement.setString(1, siteID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String department = resultSet.getString("DepartmentName");
                String title = resultSet.getString("title");
                String limitGroup = resultSet.getString("effectiveLimitGroup");
                String siteId = resultSet.getString("site_id");

                String[] row = {department, title, limitGroup, siteId};
                limitList.add(row);
            }
        }
        disconnect();
        return limitList;
    }


}
