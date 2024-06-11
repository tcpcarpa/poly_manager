package es.polytex.integracionback.importApi.manager;

import es.polytex.integracionback.client.mapper.ClientMapper;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.importApi.db.ImportDB;
import es.polytex.integracionback.user.mapper.UserMapper;
import es.polytex.integracionback.user.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportManager extends Manager {
    private final FilesDB filesDB;
    private final ImportDB importDB;
    public ImportManager() {
        this.filesDB = new FilesDB();
        this.importDB = new ImportDB();
    }

    public boolean validar(User user) {
        return isInternet() && login(user);
    }
    public List<Users> getAllUsersImport(String id) throws SQLException {
        List<Users> listall =  filesDB.getAllUsers(id);
        return filterUsers(listall);
    }
    public static List<Users> filterUsers(List<Users> userList) {
        List<Users> filteredList = new ArrayList<>();

        for (Users user : userList) {
            Users filteredUser = new Users();
            filteredUser.setuserId(user.getUserId());
            filteredUser.setCardId(user.getCardId());
            filteredUser.setFullName(user.getFullName());
            filteredUser.setDepartmentName(user.getDepartmentName());
            filteredUser.setTitle(user.getTitle());
            filteredUser.setEffectiveLimitGroup(user.getEffectiveLimitGroup());
            filteredUser.setFutureInactive(user.getFutureInactive());
            filteredUser.setCardId2(user.getCardId2());
            filteredUser.setEmail(user.getEmail());

            if (filteredUser.hasNonNullValues()) {
                filteredList.add(filteredUser);
            }
        }
        return filteredList;
    }

    public int miniImports(List<Users> userlimpia, String siteID, User user) throws SQLException {
        int batchSize = 50;
        int totalUsers = userlimpia.size();
        int start = 0;
        int successfulImports = 0;

        while (start < totalUsers) {
            int end = Math.min(start + batchSize, totalUsers);
            List<Users> batchList = userlimpia.subList(start, end);
            try {
                String json = ClientMapper.crearJsonUsuarios(batchList, siteID);
                if (ClientMapper.importFile(UserMapper.getToken(user), json)) {
                    successfulImports++;
                }
                importDB.deleteUsers(batchList);
            } catch (IOException e) {
                throw new RuntimeException("Error durante la importación: " + e.getMessage());
            }
            start += batchSize;
        }
        return successfulImports;
    }





}
