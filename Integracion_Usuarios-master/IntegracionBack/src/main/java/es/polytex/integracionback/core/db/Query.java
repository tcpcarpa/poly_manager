package es.polytex.integracionback.core.db;

public interface Query {
    String USERS_COUNT = "SELECT COUNT(*) FROM users";
    String USERS_ALL = "SELECT * FROM users";

// ---------- INSERT -------------------------------------------------------------------------------------
    String INSERT_USERS = "INSERT INTO users (userId, cardId, firstName, lastName, DepartmentName, title, effectiveLimitGroup, " +
            "isDisabledOrDisabledDate, simpleOrExtendedModeQuantityBalance, " +
            "rfidModeItemNameGroupSHORTQuantitybalance, cardId2, email) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    String INSERT_LIMIT = "INSERT INTO gruplimit (nom, codi, grup) VALUES (?, ?, ?);";
    String INSERT_SITE = "INSERT INTO site (id, name, mode, date) VALUES (?, ?, ?, ?);";


    String UPDATE_USERS = "UPDATE users SET cardId = ?, firstName=?, lastName=?, DepartmentName=?, title=?, effectiveLimitGroup=?, isDisabledOrDisabledDate=?, simpleOrExtendedModeQuantityBalance=?, rfidModeItemNameGroupSHORTQuantitybalance=?, cardId2=?, email=? WHERE userId = ?;";
    String UPDATE_LIMIT = "UPDATE users SET title = (SELECT codi FROM gruplimit WHERE gruplimit.nom = users.DepartmentName),effectiveLimitGroup = (SELECT color FROM gruplimit WHERE gruplimit.nom = users.DepartmentName);";


}
