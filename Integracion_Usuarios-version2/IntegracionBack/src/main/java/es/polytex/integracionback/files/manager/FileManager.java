package es.polytex.integracionback.files.manager;

import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.files.mapper.FilesMapper;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileManager extends Manager {
    private final SiteDB siteDB;
    private final FilesDB filesDB;

    public FileManager() {
        siteDB = new SiteDB();
        filesDB = new FilesDB();
    }

    public List<String[]> uploadFile(InputStream inputStream) throws IOException {
        List<String[]> todos = FilesMapper.leerCSV(inputStream);
        if (!todos.isEmpty()) {
            return FilesMapper.solosUsuarios(todos);
        } else {
            return new ArrayList<>();
        }
    }

    public ConfigDefinition getUserdefiniton(String siteID) throws SQLException {
        return filesDB.getUserDefinitionBySiteID(siteID);
    }

    public List<Users> getUsersEnd(List<String[]> listEnd, ConfigDefinition userDefinition, String siteID) {
        return FilesMapper.mapToUsers(listEnd, userDefinition, siteID);
    }

    public Site setUsers(List<Users> listEnd, String id) throws SQLException {
            Site s = siteDB.getSiteById(id);
            List<Users> usersLi = filesDB.getAllUsers(id);
            List<Users> usersToUpdate = new ArrayList<>();
            List<Users> usersToInsert = new ArrayList<>();

            for (Users userFromListEnd : listEnd) {
                boolean found = false;
                for (Users userFromDB : usersLi) {
                    if (userFromDB.getUserId().equals(userFromListEnd.getUserId())) {
                        usersToUpdate.add(userFromListEnd);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    usersToInsert.add(userFromListEnd);
                }
            }
            if (!usersToUpdate.isEmpty()) {
                filesDB.actualizarUsuarios(usersToUpdate, id);
            }
            if (!usersToInsert.isEmpty()) {
                filesDB.insertUsers(usersToInsert, id);
            }
            List<Users> allUsers = filesDB.getAllUsers(id);
            s.setNumUsers(String.valueOf(allUsers.size()));
            s.setLastUpdate(String.valueOf(mostrarFechaHoraAmigable()));
            filesDB.updateSite(s);
            return s;

    }

}
