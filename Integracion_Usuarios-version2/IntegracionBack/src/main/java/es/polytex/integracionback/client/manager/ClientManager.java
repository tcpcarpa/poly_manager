package es.polytex.integracionback.client.manager;

import es.polytex.integracionback.client.db.ClientDB;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.model.Site;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientManager extends Manager {
    private final SiteDB siteDB;
    private final FilesDB filesDB;
    private final ClientDB clientDB;

    public ClientManager() {
        this.siteDB = new SiteDB();
        this.filesDB = new FilesDB();
        this.clientDB = new ClientDB();
    }

    public List<String[]> getLimit(String siteID) throws SQLException {
        return clientDB.getlimit(siteID);
    }
    public List<Users> getAllUsers(String id) throws SQLException {
        List<Users> usercomprimir = comprimir(id);
        List<Users> userfiltrar = filtrarUsuariosImport(usercomprimir);
        return userfiltrar;
    }


    public List<Users> comprimir(String id) throws SQLException {
        List<Users> usersConFullName = new ArrayList<>();
        List<Users> listaTotal = filesDB.getAllUsers(id);
        for (Users user : listaTotal) {
            String fullName = user.getFirstName() + " " + user.getLastName();
            user.setFullName(fullName);
            usersConFullName.add(user);
        }
        return usersConFullName;
    }
    public List<Users> filtrarUsuariosImport(List<Users> usersList) {
        List<Users> usersFiltrados = new ArrayList<>();
        for (Users user : usersList) {
            if (tieneCamposRequeridos(user)) {
                usersFiltrados.add(user);
            }
        }
        return usersFiltrados;
    }

    public static boolean tieneCamposRequeridos(Users user) {
        return user.getUserId() != null && !user.getUserId().isEmpty() &&
                user.getCardId() != null && !user.getCardId().isEmpty() &&
                user.getFullName() != null && !user.getFullName().isEmpty() &&
                user.getDepartmentName() != null && !user.getDepartmentName().isEmpty() &&
                user.getTitle() != null && !user.getTitle().isEmpty() &&
                user.getEffectiveLimitGroup() != null && !user.getEffectiveLimitGroup().isEmpty();
    }

    public List<Users> updateUsersEffectiveLimitGroup(List<Users> userList, List<String[]> listalimit, String siteID) {
        List<Users> updatedUsersList = new ArrayList<>();
        for (Users user : userList) {
            String department = user.getDepartmentName();
            String title = user.getTitle();
            String userSiteID = user.getSite_id();

            for (String[] row : listalimit) {
                String limitDepartment = row[0];
                String limitTitle = row[1];
                String limitGroup = row[2];

                if (limitDepartment.equals(department) && limitTitle.equals(title) && (siteID.equals(userSiteID))) {
                    user.setEffectiveLimitGroup(limitGroup);
                    updatedUsersList.add(user);
                    break;
                }
            }
        }
        return updatedUsersList;
    }

    public List<Users> removeDuplicateUsers(List<Users> userList) {
        List<Users> uniqueUsers = new ArrayList<>();
        Map<String, Users> userMap = new HashMap<>();

        for (Users user : userList) {
            String key = user.getKeyForComparison();

            if (userMap.containsKey(key)) {
                Users existingUser = userMap.get(key);
                if (user.getNumAttributes() > existingUser.getNumAttributes()) {
                    userMap.put(key, user);
                }
            } else {
                userMap.put(key, user);
            }
        }
        uniqueUsers.addAll(userMap.values());

        return uniqueUsers;
    }
    public boolean insaAll(List<Users> uslist, String id) throws SQLException {
        boolean success = false;
        if (filesDB.actualizarUsuarios(uslist, id)) {
            Site s = siteDB.getSiteById(id);
            s.setLastUpdate(String.valueOf(mostrarFechaHoraAmigable()));
            s.setLaslimit(String.valueOf(mostrarFechaHoraAmigable()));
            s.setNumUsers(String.valueOf(uslist.size()));
            filesDB.updateSite(s);
            success = true;
        }
        return success;
    }
}