package es.polytex.integracionback.core.db;

public interface Query {
    String SELECT_PATHS = "SELECT path_usuarios, path_limitGrup FROM site WHERE site_id =?";
    String UPDATE_PATHS = "UPDATE site SET path_limitGrup = ?, path_usuarios =? WHERE site_id = ?";
    String SELECT_UPDATEDATA = "SELECT fecha_update FROM site WHERE site_id =?";
    String UPDATE_UPDATEDATA = "UPDATE site SET fecha_update = ? WHERE site_id = ?";

    //---------------------------AUTO-----------------------------------------------------------------------------------
    String UPDATELIMIT_ALLUSERS = "UPDATE users SET effectiveLimitGroup = ? WHERE DepartmentName = ? AND title = ? AND site_id = ?";
    String SELECT_LIMIT_ALL = "SELECT DepartmentName, title, effectiveLimitGroup, site_id FROM gruplimit WHERE site_id = ?";

    // --------- LIMIT ------------------------------------------------------------------------------------------------
    String UPDATE_GRUPLIMIT = "UPDATE users u " +
            "JOIN gruplimit g ON u.DepartmentName = g.DepartmentName AND u.title = g.title " +
            "SET u.effectiveLimitGroup = g.effectiveLimitGroup";
    String SELECT_LIMIT = "SELECT * FROM gruplimit WHERE site_id = ?";
    String UPDATE_LIMIT = "UPDATE gruplimit SET effectiveLimitGroup = ? WHERE DepartmentName = ? AND title = ? AND site_id = ?";
    String INSERT_LIMIT = "INSERT INTO gruplimit (DepartmentName, title, effectiveLimitGroup, site_id) VALUES (?, ?, ?, ?)";
    ;
    // --------- USERS ------------------------------------------------------------------------------------------------
    String SELECT_ALL_USERS = "SELECT * FROM users WHERE site_id =?";
    String DELETE_USERS = "DELETE FROM users WHERE userId = ?";
    String INSERT_USERS = "INSERT INTO users (userId, cardId, firstName, lastName, fullName, DepartmentName, title, effectiveLimitGroup, futureInactiveData, cardId2, email,site_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
    String UPDATE_USERS = "UPDATE users SET cardId = ?, firstName = ?, lastName = ?, fullName =?, DepartmentName = ?, title = ?, effectiveLimitGroup = ?, futureInactiveData  = ?, cardId2 = ?, email = ?, site_id=? WHERE userId = ?";

    // ------------------- USEREF ----------------------------------------------------------------------------
    String INSERT_USERDEF = "INSERT INTO configDefinition (userId, cardId, firstName, lastName,fullName, DepartmentName, effectiveLimitGroup, futureInactiveData, cardId2, email, title, dateFormat, numFormat, site_id) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
    String UPDATE_USERDEF = "UPDATE configDefinition SET userId = ?, cardId = ?, firstName=?, lastName=?,fullName=?, DepartmentName = ?, title = ?, effectiveLimitGroup = ?, futureInactiveData = ?, cardId2 = ?, email = ?, dateFormat=?, numFormat=? WHERE site_id = ?;";
    String SELECT_USERDEF_ALL = "SELECT * FROM configDefinition JOIN site ON configDefinition.site_id = site.site_id WHERE site.site_id = ?";

    // ------------------- USERDEF_BOOL --------------------------------------------------------------------------------------
    String SELECT_USERDEF_BOOL_SITE = "SELECT userDefinition FROM site WHERE site_id = ?;";
    String UPDATE_USERDEF_BOOL_SITE = "UPDATE site SET userDefinition = ? WHERE site_id = ?";

    // ------------------- SITE --------------------------------------------------------------------------------------
    String SELECT_SITE = "SELECT * FROM site WHERE site_id = ?";
    String UPDATE_SITE = "UPDATE site SET name = ?, mode = ?, userdefinition = ?,  numUsers=?, lastUpdate=?,  lastlimit=?, last_import=?, path_limitGrup=?, path_usuarios=?, fecha_update=? WHERE site_id = ?;";
    String INSERT_SITE = "INSERT INTO site (site_id, name, mode, userdefinition, numUsers, lastUpdate, lastLimit, last_import, path_limitGrup, path_usuarios, fecha_update) VALUES (?,?,?,?, ?, ?, ?,?,?,?,?);";

    // ------------------- USER --------------------------------------------------------------------------------------
    String INSERT_USER = "INSERT INTO user (user_id, username, password, numSites) VALUES(?,?,?,?);";
    String INSERT_USER_SITELIST = "INSERT INTO user_site (username, site_id) VALUES (?, ?)";
    String SELECT_SITELIST = "SELECT s.* FROM site s INNER JOIN user_site us ON s.site_id = us.site_id WHERE us.username = ?";
    String DELETE_USER_SITELIST = "DELETE FROM user_site WHERE username = ?";
    String USER_EXISTS = "SELECT * FROM user WHERE username = ?;";

    // ------------------- AUTO --------------------------------------------------------------------------------------
    String SELECT_TIME = "SELECT c FROM Tiempo c";
    String SELECT_PATH_LIM =  "SELECT path_limitGrup FROM site WHERE site_id = ?";;
    String SELECT_PATH_USU =  "SELECT path_usuarios FROM site WHERE site_id = ?";;

}