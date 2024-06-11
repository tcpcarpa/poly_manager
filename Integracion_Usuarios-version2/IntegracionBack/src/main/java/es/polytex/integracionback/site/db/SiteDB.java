package es.polytex.integracionback.site.db;

import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteDB extends DB {
    public Site getSiteById(String siteId) throws SQLException {
        connect();
        Site site = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SELECT_SITE)) {
            preparedStatement.setString(1, siteId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    site = new Site();
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
                    site.setSite_id(resultSet.getString("site_id"));
                }
            }
        }
        disconnect();
        return site;
    }

    public String getUserDefinitionBool(String siteID) throws SQLException {
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SELECT_USERDEF_BOOL_SITE)) {
            preparedStatement.setString(1, siteID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("userDefinition");
                }
            }
        }
        disconnect();
        return "NO";
    }

    public ConfigDefinition getUserDefinitionBySite(Site site) throws SQLException {
        ConfigDefinition userDefinition = null;
        connect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SELECT_USERDEF_ALL)) {
            preparedStatement.setString(1, site.getSite_id());
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
                    userDefinition.setDateFormat(resultSet.getString("dateFormat"));
                    userDefinition.setNumberFormat(resultSet.getString("NumFormat"));
                    userDefinition.setSiteID(site.getSite_id());
                }
            }
            disconnect();
            return userDefinition;
        }
    }

}

