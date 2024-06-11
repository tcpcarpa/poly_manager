package es.polytex.integracionback.importApi.controller;

import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.importApi.manager.ImportManager;
import es.polytex.integracionback.importApi.mapper.ImportMapper;
import es.polytex.integracionback.user.model.User;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class ImportController extends Controller {
    private static final ImportManager importManager = new ImportManager();
    private static ImportController controller;
    public static ImportController getInstance() {
        if (controller == null) {
            controller = new ImportController();
        }
        return controller;
    }

    public Response loginImport(String id, String u) {
        response = null;
        try {
            User user = ImportMapper.getUser(u);
                if (importManager.validar(user)) {
                    List<Users> listauserDB = importManager.getAllUsersImport(id);
                    if (!listauserDB.isEmpty()) {
                        int count = importManager.miniImports(listauserDB, id, user);
                        if (count != 0) {
                            response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, count);
                        } else {
                            response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                        }
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_PERMISION, null);
                    }
                } else {
                    response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_PERMISION, null);
                }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
