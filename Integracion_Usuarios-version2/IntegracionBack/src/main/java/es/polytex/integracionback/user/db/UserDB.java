package es.polytex.integracionback.user.db;

import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.user.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDB extends DB {
    public User selectUser(String nom) throws SQLException {
        User user = null;
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.USER_EXISTS)) {
            preparedStatement.setString(1, nom);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUser_id(rs.getString("user_id"));
                    user.setUsername(nom);
                    user.setPassword(rs.getString("password"));
                    user.setNumSites(rs.getInt("numSites"));
                }
                disconnect();
            }
            return user;
        }
    }

    public boolean insertUser(User user) throws SQLException {
        boolean success = false;
        connect();
        try {
            try (PreparedStatement insertStatement = connection.prepareStatement(Query.INSERT_USER)) {
                insertStatement.setString(1, user.getUser_id());
                insertStatement.setString(2, user.getUsername());
                insertStatement.setString(3, user.getPassword());
                insertStatement.setInt(4, user.getNumSites());

                int rowsAffected = insertStatement.executeUpdate();
                success = rowsAffected > 0;
            }
            return success;
        } finally {
            disconnect();
        }
    }

    public boolean insertSite(List<Site> siteList) throws SQLException {
        boolean success = false;
        connect();
        try {
            for (Site site : siteList) {
                try (PreparedStatement insertStatement = connection.prepareStatement(Query.INSERT_SITE)) {
                    insertStatement.setString(1, site.getId());
                    insertStatement.setString(2, site.getName());
                    insertStatement.setString(3, site.getMode() != null ? site.getMode() : "0");
                    insertStatement.setString(4, site.getUserDefinition() != null ? site.getUserDefinition() : "NO");
                    insertStatement.setString(5, site.getNumUsers() != null ? site.getNumUsers() : "No hay Usuarios");
                    insertStatement.setString(6, site.getLastUpdate() != null ? site.getLastUpdate() : "Sin Update Realizado");
                    insertStatement.setString(7, site.getLaslimit() != null ? site.getLaslimit() : "Sin Grupo Limite Realizado");
                    insertStatement.setString(8, site.getLast_import() != null ? site.getLast_import() : "Sin Import API Realizado");
                    insertStatement.setString(9, site.getPath_limitGrup() != null ? site.getPath_limitGrup() : "Sin Destino de Grupo Limite");
                    insertStatement.setString(10, site.getPath_usuarios() != null ? site.getPath_usuarios() : "Sin Destino de Usuarios");
                    insertStatement.setString(11, site.getFecha_update() != null ? site.getPath_usuarios() : "Sin Fecha Establecida");
                    int rowsAffected = insertStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        success = true;
                    }
                }
            }
            return success;
        } finally {
            disconnect();
        }
    }
    public List<Site> selectListSiteUser(User user) throws SQLException {
        List<Site> sites = new ArrayList<>();
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SELECT_SITELIST)) {
            preparedStatement.setString(1, user.getUsername());
            System.out.println(user.getUsername());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Site site = new Site();
                    site.setSite_id(resultSet.getString("site_id"));
                    site.setName(resultSet.getString("name"));
                    site.setMode(resultSet.getString("mode"));
                    site.setUserDefinition(resultSet.getString("userdefinition"));
                    site.setNumUsers(resultSet.getString("numUsers"));
                    site.setLastUpdate(resultSet.getString("lastUpdate"));
                    site.setLaslimit(resultSet.getString("lastlimit"));
                    site.setLast_import(resultSet.getString("last_import"));
                    site.setPath_limitGrup(resultSet.getString("path_limitGrup"));
                    site.setPath_usuarios(resultSet.getString("path_usuarios"));
                    site.setFecha_update(resultSet.getString("fecha_update"));

                    sites.add(site);
                }
                disconnect();
                return sites;
            }
        }
    }

    public boolean insertListSiteUser(User user, List<Site> siteList) throws SQLException {
        boolean inserted = false;
        connect();
        for (Site site : siteList) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_USER_SITELIST)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, site.getSite_id());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    inserted = true;
                }
            }
        }
        inserted = false;
        disconnect();
        return inserted;
    }


    public boolean updateListSiteUser(User user, List<Site> siteList) throws SQLException {
        boolean updated = false;
        connect();
        try (PreparedStatement deleteStatement = connection.prepareStatement(Query.DELETE_USER_SITELIST)) {
            deleteStatement.setString(1, user.getUsername());
            int rowsDeleted = deleteStatement.executeUpdate();
            if (rowsDeleted > 0) {
                updated = true;
            }
            if (insertListSiteUser(user, siteList)) {
                updated = false;
                disconnect();
            }
        }
        return updated;
    }
}
