package es.polytex.integracionback.client.db;

import com.fasterxml.jackson.databind.JsonNode;
import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ClientDB extends DB {
    public void insertUsers(JsonNode usersNode) throws SQLException {
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_USERS)) {
            for (JsonNode userNode : usersNode) {
                preparedStatement.setString(1, userNode.get("userId").asText());
                preparedStatement.setString(2, userNode.get("cardId").asText());
                preparedStatement.setString(3, userNode.get("firstName").asText());
                preparedStatement.setString(4, userNode.get("lastName").asText());
                preparedStatement.setString(5, userNode.get("DepartmentName").asText());
                preparedStatement.setString(6, userNode.get("title").asText());
                preparedStatement.setString(7, userNode.get("effectiveLimitGroup").asText());
                preparedStatement.setString(8, userNode.get("isDisabledOrDisabledDate").asText());
                preparedStatement.setString(9, userNode.get("simpleOrExtendedModeQuantityBalance").asText());
                preparedStatement.setString(10, userNode.get("rfidModeItemNameGroupSHORTQuantitybalance").asText());
                preparedStatement.setString(11, userNode.get("cardId2").asText());
                preparedStatement.setString(12, userNode.get("email").asText());

                preparedStatement.executeUpdate();
            }
        }
        disconnect();
    }
}


