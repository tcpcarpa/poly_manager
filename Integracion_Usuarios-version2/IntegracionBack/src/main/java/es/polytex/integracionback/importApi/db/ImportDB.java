package es.polytex.integracionback.importApi.db;

import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImportDB extends DB {
    public boolean deleteUsers(List<Users> usersList) throws SQLException {
        connect();
        try (PreparedStatement statement = connection.prepareStatement(Query.DELETE_USERS)) {
            for (int i = 0; i < usersList.size(); i++) {
                statement.setString(i + 1, usersList.get(i).getUserId());
            }
            int rowsAffected = statement.executeUpdate();
            disconnect();
            return rowsAffected > 0;
        }
    }
}
