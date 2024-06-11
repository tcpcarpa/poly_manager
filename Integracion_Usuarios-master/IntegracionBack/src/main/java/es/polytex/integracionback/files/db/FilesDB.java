package es.polytex.integracionback.files.db;

import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilesDB extends DB {


    public boolean isTablaUsuariosVacia() {
        try {
            connect();
            try (PreparedStatement statement = connection.prepareStatement(Query.USERS_COUNT);
                 ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
            disconnect();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return false;
    }

    public List<Users> obtenerTodosLosUsuarios() {
        List<Users> listaUsuarios = new ArrayList<>();
        try {
            connect();
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.USERS_ALL)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Users usuario = new Users();
                    usuario.setuserId(resultSet.getString("userId"));
                    usuario.setCardId(resultSet.getString("cardId"));
                    usuario.setFirstName(resultSet.getString("firstName"));
                    usuario.setLastName(resultSet.getString("lastName"));
                    usuario.setDepartmentName(resultSet.getString("DepartmentName"));
                    usuario.setTitle(resultSet.getString("title"));
                    usuario.setEffectiveLimitGroup(resultSet.getString("effectiveLimitGroup"));
                    usuario.setIsDisabledOrDisabledDate(resultSet.getString("isDisabledOrDisabledDate"));
                    usuario.setRfidModeItemNameGroupSHORTQuantitybalance(resultSet.getString("rfidModeItemNameGroupSHORTQuantitybalance"));
                    usuario.setCardId2(resultSet.getString("cardId2"));
                    usuario.setEmail(resultSet.getString("email"));

                    listaUsuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return listaUsuarios;
    }

    public void insLimit(List<String[]> rowData) {
        try {
            connect();
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_LIMIT)) {
                for (String[] values : rowData) {
                    preparedStatement.setString(1, values[0].trim());
                    preparedStatement.setString(2, values[1].trim());
                    preparedStatement.setString(3, values[2].trim());
                    preparedStatement.executeUpdate();
                }
                disconnect();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void actualizarUsuarios(List<Users> listaUsuarios) {
        try {
            connect();
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.UPDATE_USERS)) {
                for (Users usuario : listaUsuarios) {
                    preparedStatement.setString(1, usuario.getCardId());
                    preparedStatement.setString(2, usuario.getFirstName());
                    preparedStatement.setString(3, usuario.getLastName());
                    preparedStatement.setString(4, usuario.getDepartmentName());
                    preparedStatement.setString(5, usuario.getTitle());
                    preparedStatement.setString(6, usuario.getEffectiveLimitGroup());
                    preparedStatement.setString(7, usuario.getIsDisabledOrDisabledDate());
                    preparedStatement.setString(8, usuario.getSimpleOrExtendedModeQuantityBalance());
                    preparedStatement.setString(9, usuario.getRfidModeItemNameGroupSHORTQuantitybalance());
                    preparedStatement.setString(10, usuario.getCardId2());
                    preparedStatement.setString(11, usuario.getEmail());
                    preparedStatement.setString(12, usuario.getUserId());

                    preparedStatement.executeUpdate();
                }
                disconnect();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public void insertUsers(List<Users> userList) {
        try {
            connect();
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_USERS)) {
                for (Users user : userList) {
                    preparedStatement.setString(1, user.getUserId());
                    preparedStatement.setString(2, user.getCardId());
                    preparedStatement.setString(3, user.getFirstName());
                    preparedStatement.setString(4, user.getLastName());
                    preparedStatement.setString(5, user.getDepartmentName());
                    preparedStatement.setString(6, user.getTitle());
                    preparedStatement.setString(7, user.getEffectiveLimitGroup());
                    preparedStatement.setString(8, user.getIsDisabledOrDisabledDate());
                    preparedStatement.setString(9, user.getSimpleOrExtendedModeQuantityBalance());
                    preparedStatement.setString(10, user.getRfidModeItemNameGroupSHORTQuantitybalance());
                    preparedStatement.setString(11, user.getCardId2());
                    preparedStatement.setString(12, user.getEmail());
                    preparedStatement.executeUpdate();
                }
            disconnect();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}





