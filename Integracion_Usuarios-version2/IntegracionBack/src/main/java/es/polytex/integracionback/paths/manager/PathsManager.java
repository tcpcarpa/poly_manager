package es.polytex.integracionback.paths.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.paths.mapper.PathsMapper;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.model.Site;

import java.sql.SQLException;

public class PathsManager extends Manager {
    private final SiteDB siteDB;
    private final FilesDB filesDB;

    public PathsManager() {
        this.siteDB = new SiteDB();
        this.filesDB = new FilesDB();
    }

    public Site setPaths(String siteID, String jsonresult) throws SQLException {
        Site s = siteDB.getSiteById(siteID);
        try {
            s.setPath_usuarios(PathsMapper.extraerParamPathUsers(jsonresult));
            s.setPath_limitGrup(PathsMapper.extraerParamPathLimit(jsonresult));
            filesDB.updateSite(s);
            return s;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
