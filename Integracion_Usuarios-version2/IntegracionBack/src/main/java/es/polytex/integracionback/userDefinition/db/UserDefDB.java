package es.polytex.integracionback.userDefinition.db;

import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDefDB extends DB {
    public boolean insertUserDef(ConfigDefinition userDefinition, String siteId) throws SQLException {
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_USERDEF)) {
            preparedStatement.setInt(1, userDefinition.getUserId());
            preparedStatement.setInt(2, userDefinition.getCardId());
            preparedStatement.setInt(3, userDefinition.getFirstName());
            preparedStatement.setInt(4, userDefinition.getLastName());
            preparedStatement.setInt(5, userDefinition.getFullName());
            preparedStatement.setInt(6, userDefinition.getDepartment());
            preparedStatement.setInt(7, userDefinition.getLimit());
            preparedStatement.setInt(8, userDefinition.getEnddate());
            preparedStatement.setInt(9, userDefinition.getCardId2());
            preparedStatement.setInt(10, userDefinition.getEmail());
            preparedStatement.setInt(11, userDefinition.getTitle());
            preparedStatement.setString(12, userDefinition.getDateFormat());
            preparedStatement.setString(13, userDefinition.getNumberFormat());
            preparedStatement.setString(14, siteId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            disconnect();
        }
    }
    public boolean updateUserDef(ConfigDefinition userDefinition, String siteId) throws SQLException {
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_USERDEF)) {
            preparedStatement.setInt(1, userDefinition.getUserId());
            preparedStatement.setInt(2, userDefinition.getCardId());
            preparedStatement.setInt(3, userDefinition.getFirstName());
            preparedStatement.setInt(4, userDefinition.getLastName());
            preparedStatement.setInt(5, userDefinition.getFullName());
            preparedStatement.setInt(6, userDefinition.getDepartment());
            preparedStatement.setInt(7, userDefinition.getTitle());
            preparedStatement.setInt(8, userDefinition.getLimit());
            preparedStatement.setInt(9, userDefinition.getEnddate());
            preparedStatement.setInt(10, userDefinition.getCardId2());
            preparedStatement.setInt(11, userDefinition.getEmail());
            preparedStatement.setString(12, userDefinition.getDateFormat());
            preparedStatement.setString(13, userDefinition.getNumberFormat());
            preparedStatement.setString(14, siteId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            disconnect();
        }
    }

    public boolean updateUserDefBool(String siteId) throws SQLException {
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_USERDEF_BOOL_SITE)) {
            preparedStatement.setString(1, "YES");
            preparedStatement.setString(2, siteId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            disconnect();
        }
    }



}




