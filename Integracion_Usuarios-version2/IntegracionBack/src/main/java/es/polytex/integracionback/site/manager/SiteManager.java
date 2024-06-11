package es.polytex.integracionback.site.manager;

import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;

import java.sql.SQLException;

public class SiteManager {
    private final SiteDB siteDB;
    public SiteManager() {
        this.siteDB = new SiteDB();
    }

    public ConfigDefinition getUserDefinition(Site site) {
        try {
            String existUserDef = siteDB.getUserDefinitionBool(site.getSite_id());
            if ("YES".equals(existUserDef)) {
                return siteDB.getUserDefinitionBySite(site);
            } else {
                return new ConfigDefinition(1,2,3,4,5,6,7,8,9,10,11,"YYYY-MM-DD","Hexadecimal", site.getSite_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Site getSite(String siteID) throws SQLException {
        return siteDB.getSiteById(siteID);
    }
    public Site getSite(Site site) throws SQLException {
        return siteDB.getSiteById(site.getSite_id());
    }
}
