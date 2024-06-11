package es.polytex.integracionback.user.manager;

import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.site.mapper.SiteMapper;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.user.db.UserDB;
import es.polytex.integracionback.user.mapper.UserMapper;
import es.polytex.integracionback.user.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserManager extends Manager {
    private final UserDB userDB;
    public UserManager() {
        this.userDB = new UserDB();
    }

    public boolean validar(User user) {
        return isInternet() && login(user);
    }

    public List<Site> getSites(User user) throws IOException {
        return SiteMapper.getSiteLIST(SiteMapper.getSites(UserMapper.getToken(user)));
    }

    public User getUser(User user, List<Site> sites) {
            try {
                User u = userDB.selectUser(user.getUsername());
                if (u != null) {
                    List<Site> sitesenDB = userDB.selectListSiteUser(u);
                    if(sitesenDB.isEmpty()){
                        userDB.insertSite(sites);
                    }
                    List<Site> sitesSoloEnApi = UserMapper.sitesnoDB(sites, sitesenDB);
                    if (!sitesSoloEnApi.isEmpty()) {
                        userDB.insertSite(sitesSoloEnApi);
                    }
                    return u;
                } else {
                    user = new User(user.getUsername(), user.getPassword(), sites, sites.size());
                    if (userDB.insertUser(user) && userDB.insertSite(sites) && userDB.insertListSiteUser(user, sites)) {
                        return user;
                    } else {
                        return null;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


}
