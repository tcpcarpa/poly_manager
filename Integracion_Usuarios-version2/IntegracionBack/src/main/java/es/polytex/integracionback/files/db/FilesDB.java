package es.polytex.integracionback.files.db;

import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;
import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;
import es.polytex.integracionback.site.model.Site;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilesDB extends DB {

    public ConfigDefinition getUserDefinitionBySiteID(String siteID) throws SQLException {
        ConfigDefinition userDefinition = null;
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SELECT_USERDEF_ALL)) {
            preparedStatement.setString(1, siteID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userDefinition = new ConfigDefinition();
                    userDefinition.setUserId(resultSet.getInt("userId"));
                    userDefinition.setCardId(resultSet.getInt("cardId"));
                    userDefinition.setFirstName(resultSet.getInt("firstName"));
                    userDefinition.setLastName(resultSet.getInt("lastName"));
                    userDefinition.setFullName(resultSet.getInt("fullName"));
                    userDefinition.setDepartment(resultSet.getInt("DepartmentName"));
                    userDefinition.setTitle(resultSet.getInt("title"));
                    userDefinition.setLimit(resultSet.getInt("effectiveLimitGroup"));
                    userDefinition.setEnddate(resultSet.getInt("futureInactiveData"));
                    userDefinition.setCardId2(resultSet.getInt("cardId2"));
                    userDefinition.setEmail(resultSet.getInt("email"));
                }
            }
            disconnect();
            return userDefinition;
        }
    }

    public boolean insertUsers(List<Users> userList, String id) throws SQLException {
        boolean success = false;
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_USERS)) {
            for (Users usuario : userList) {
                preparedStatement.setString(1, usuario.getUserId() != null ? usuario.getUserId() : "0");
                preparedStatement.setString(2, usuario.getCardId() != null ? usuario.getCardId() : "0");
                preparedStatement.setString(3, usuario.getFirstName() != null ? usuario.getFirstName() : "0");
                preparedStatement.setString(4, usuario.getLastName() != null ? usuario.getLastName() : "0");
                preparedStatement.setString(5, usuario.getFullName() != null ? usuario.getFullName() : "0");
                preparedStatement.setString(6, usuario.getDepartmentName() != null ? usuario.getDepartmentName() : "0");
                preparedStatement.setString(7, usuario.getTitle() != null ? usuario.getTitle() : "0");
                preparedStatement.setString(8, usuario.getEffectiveLimitGroup() != null ? usuario.getEffectiveLimitGroup() : "0");
                preparedStatement.setString(9, usuario.getFutureInactive() != null ? usuario.getFutureInactive() : "0");
                preparedStatement.setString(10, usuario.getCardId2() != null ? usuario.getCardId2() : "0");
                preparedStatement.setString(11, usuario.getEmail() != null ? usuario.getEmail() : "0");
                preparedStatement.setString(12, id);

                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    success = true;
                }
            }
            return success;
        } finally {
            disconnect();
        }
    }


    public boolean actualizarUsuarios(List<Users> listaUsuarios, String id) throws SQLException {
        boolean actualizacionExitosa = false;
            connect();
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_USERS)) {
                for (Users usuario : listaUsuarios) {
                    preparedStatement.setString(1, usuario.getCardId() != null ? usuario.getCardId() : "0");
                    preparedStatement.setString(2, usuario.getFirstName() != null ? usuario.getFirstName() : "0");
                    preparedStatement.setString(3, usuario.getLastName() != null ? usuario.getLastName() : "0");
                    preparedStatement.setString(4, usuario.getFullName() != null ? usuario.getFullName() : "0");
                    preparedStatement.setString(5, usuario.getDepartmentName() != null ? usuario.getDepartmentName() : "0");
                    preparedStatement.setString(6, usuario.getTitle() != null ? usuario.getTitle() : "0");
                    preparedStatement.setString(7, usuario.getEffectiveLimitGroup() != null ? usuario.getEffectiveLimitGroup() : "0");
                    preparedStatement.setString(8, usuario.getFutureInactive() != null ? usuario.getFutureInactive() : "0");
                    preparedStatement.setString(9, usuario.getCardId2() != null ? usuario.getCardId2() : "0");
                    preparedStatement.setString(10, usuario.getEmail() != null ? usuario.getEmail() : "0");
                    preparedStatement.setString(11, id);
                    preparedStatement.setString(12, usuario.getUserId());
                    preparedStatement.executeUpdate();
                }
                actualizacionExitosa = true;

        }
        disconnect();
        return actualizacionExitosa;
    }

    public List<Users> getAllUsers(String siteId) throws SQLException {
        connect();
        List<Users> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(Query.SELECT_ALL_USERS)) {
            statement.setString(1, siteId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Users user = new Users();
                    user.setuserId(resultSet.getString("userId"));
                    user.setCardId(resultSet.getString("cardId"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setFullName(resultSet.getString("fullName"));
                    user.setDepartmentName(resultSet.getString("DepartmentName"));
                    user.setTitle(resultSet.getString("title"));
                    user.setEffectiveLimitGroup(resultSet.getString("effectiveLimitGroup"));
                    user.setFutureInactive(resultSet.getString("futureInactiveData"));
                    user.setCardId2(resultSet.getString("cardId2"));
                    user.setEmail(resultSet.getString("email"));
                    user.setSite_id(resultSet.getString("site_id"));
                    users.add(user);
                }
            }
            return users;
        } finally {
            disconnect();
        }
    }

    public boolean updateSite(Site site) throws SQLException {
        boolean success = false;
        connect();
        try {
            try (PreparedStatement updateStatement = connection.prepareStatement(Query.UPDATE_SITE)) {
                updateStatement.setString(1, site.getName());
                updateStatement.setString(2, site.getMode() != null ? site.getMode() : "0");
                updateStatement.setString(3, site.getUserDefinition() != null ? site.getUserDefinition() : "NO");
                updateStatement.setString(4, site.getNumUsers() != null ? site.getNumUsers() : "No hay Usuarios");
                updateStatement.setString(5, site.getLastUpdate() != null ? site.getLastUpdate() : "Sin Update Realizado");
                updateStatement.setString(6, site.getLaslimit() != null ? site.getLaslimit() : "Sin Limite_Grup Realizado");
                updateStatement.setString(7, site.getLast_import() != null ? site.getLast_import() : "Sin Limite_Grup Realizado");
                updateStatement.setString(8, site.getPath_limitGrup() != null ? site.getPath_limitGrup() : "Sin Destino del Limite");
                updateStatement.setString(9, site.getPath_usuarios() != null ? site.getPath_usuarios() : "Sin Destino de Usuarios");
                updateStatement.setString(10, site.getFecha_update() != null ? site.getFecha_update() : "Sin Fecha de Actualización");
                updateStatement.setString(11, site.getSite_id());
                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
            }
            return success;
        } finally {
            disconnect();
        }
    }


}









