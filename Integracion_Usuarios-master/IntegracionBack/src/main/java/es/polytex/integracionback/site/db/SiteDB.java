package es.polytex.integracionback.site.db;

import es.polytex.integracionback.core.db.DB;
import es.polytex.integracionback.core.db.Query;
import es.polytex.integracionback.site.model.Site;

import java.sql.PreparedStatement;

public class SiteDB extends DB {
    public void insertSite(Site site) {
        try {
            connect();
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.INSERT_SITE)) {
                preparedStatement.setInt(1, site.getId());
                preparedStatement.setString(2, site.getName());
                preparedStatement.setString(3, site.getMode());

                preparedStatement.executeUpdate();

                disconnect();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
