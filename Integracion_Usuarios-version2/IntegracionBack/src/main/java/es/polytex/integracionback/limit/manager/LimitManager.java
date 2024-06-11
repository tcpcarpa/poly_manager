package es.polytex.integracionback.limit.manager;

import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.files.mapper.FilesMapper;
import es.polytex.integracionback.limit.db.LimitDB;
import es.polytex.integracionback.limit.mapper.LimitMaper;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.model.Site;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LimitManager extends Manager {
    private final LimitDB limitDB;
    private final SiteDB siteDB;
    private final FilesDB filesDB;

    public LimitManager() {
        this.limitDB = new LimitDB();
        this.siteDB = new SiteDB();
        this.filesDB = new FilesDB();
    }

    public List<String[]> procesarDatos(InputStream inputStream) throws IOException {
        List<String[]> fileContent = FilesMapper.leerCSV(inputStream);
        List<String[]> soloMay = FilesMapper.solosUsuarios(fileContent);
        return LimitMaper.soloMayusculas(soloMay);
    }


    public List<String[]> compareAndUpdateLimits(List<String[]> limitListDB, List<String[]> newLimitList, String siteID) throws SQLException {
        List<String[]> finalLimitList = new ArrayList<>(limitListDB);
        for (String[] newLimit : newLimitList) {
            String department = newLimit[0];
            String title = newLimit[1];
            String limitGroup = newLimit[2];

            boolean found = false;
            for (String[] limit : limitListDB) {
                String dbDepartment = limit[0];
                String dbTitle = limit[1];
                String dbSiteID =siteID;

                if (department.equals(dbDepartment) && title.equals(dbTitle)){
                    limit[2] = limitGroup;
                    found = true;
                    break;
                }
            }
            if (!found) {
                finalLimitList.add(newLimit);
            }
        }
        return finalLimitList;
    }

    public static List<Users> updateEffectiveLimitGroup(List<Users> userList, List<String[]> limitList, String siteID) {
        for (Users user : userList) {
            String department = user.getDepartmentName();
            String title = user.getTitle();
            String siteId = user.getSite_id();

            for (String[] row : limitList) {
                String limitDepartment = row[0];
                String limitTitle = row[1];
                String limitGroup = row[2];

                if (limitDepartment.equals(department) && limitTitle.equals(title) && siteID.equals(siteId)) {
                    user.setEffectiveLimitGroup(limitGroup);
                    break;
                }
            }
        }
        return userList;
    }

    public Site setLimitGrup(List<String[]> listaLimit, String siteID) throws SQLException {
        List<String[]> limitListDB = limitDB.selectLimitList(siteID);
        List<Users> allUsers = filesDB.getAllUsers(siteID);
        Site s = siteDB.getSiteById(siteID);

        if (allUsers.isEmpty()) {
            limitDB.insertarGrupLimit(listaLimit, siteID);
        } else {
            if(limitListDB.isEmpty()){
                limitDB.insertarGrupLimit(listaLimit, siteID);
                List<Users> usersLista = updateEffectiveLimitGroup(allUsers, listaLimit, siteID);
                filesDB.actualizarUsuarios(usersLista, siteID);
            } else {
                List<String[]> lastLIst = compareAndUpdateLimits(limitListDB, listaLimit, siteID);
                limitDB.updateGrupLimit(lastLIst, siteID);
                List<Users> usersList = updateEffectiveLimitGroup(allUsers, lastLIst, siteID);
                filesDB.actualizarUsuarios(usersList, siteID);
            }
        }
        s.setLaslimit(mostrarFechaHoraAmigable());
        filesDB.updateSite(s);
        return s;
    }
}
