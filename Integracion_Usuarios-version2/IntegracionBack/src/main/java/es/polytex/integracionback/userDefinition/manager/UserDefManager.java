package es.polytex.integracionback.userDefinition.manager;

import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.userDefinition.db.UserDefDB;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;

import java.sql.SQLException;

public class UserDefManager extends Manager {
    private final UserDefDB userDefDB;
    private final SiteDB siteDB;
    public UserDefManager() {
        this.userDefDB = new UserDefDB();
        this.siteDB = new SiteDB();
    }

    public boolean setUserDef(ConfigDefinition userDefinition, String siteId) throws SQLException {
        String userDefBool = siteDB.getUserDefinitionBool(siteId);
        boolean done = false;
        if ("NO".equals(userDefBool)) {
            done = userDefDB.insertUserDef(userDefinition, siteId) && userDefDB.updateUserDefBool(siteId);
        } else {
            done = userDefDB.updateUserDef(userDefinition, siteId);
        }
        return done;
    }

    public ConfigDefinition cogervaloresConfig(ConfigDefinition configDefinition, String siteID) {
        ConfigDefinition userDefinition = new ConfigDefinition();
        userDefinition.setUserId(configDefinition.getUserId());
        userDefinition.setCardId(configDefinition.getCardId());
        userDefinition.setFirstName(configDefinition.getFirstName());
        userDefinition.setLastName(configDefinition.getLastName());
        userDefinition.setFullName(configDefinition.getFullName());
        userDefinition.setDepartment(configDefinition.getDepartment());
        userDefinition.setTitle(configDefinition.getTitle());
        userDefinition.setLimit(configDefinition.getLimit());
        userDefinition.setEnddate(configDefinition.getEnddate());
        userDefinition.setCardId2(configDefinition.getCardId2());
        userDefinition.setEmail(configDefinition.getEmail());
        userDefinition.setNumberFormat(configDefinition.getNumberFormat());
        userDefinition.setDateFormat(configDefinition.getDateFormat());
        userDefinition.setSiteID(siteID);

        return userDefinition;
    }

}
