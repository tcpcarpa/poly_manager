package es.polytex.integracionback.files.controller;


import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.files.manager.FileManager;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;
import java.util.List;

public class FilesController extends Controller {
    private static final FileManager fileManager = new FileManager();
    private static FilesController controller;

    public static FilesController getInstance() {
        if (controller == null) {
            controller = new FilesController();
        }
        return controller;
    }

    public Response uploadFile(String siteID, InputStream fileInputStream) {
        response = null;
        try {
            List<String[]> listaFinal = fileManager.uploadFile(fileInputStream);
            if (!listaFinal.isEmpty()) {
                ConfigDefinition userDefinition = fileManager.getUserdefiniton(siteID);
                if (userDefinition != null) {
                    List<Users> listaUsersEnd = fileManager.getUsersEnd(listaFinal, userDefinition, siteID);
                    if (!listaUsersEnd.isEmpty()) {
                        Site s = fileManager.setUsers(listaUsersEnd, siteID);
                        if (s != null) {
                            response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, s);
                        } else {
                            response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                        }
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                    }
                } else {
                    response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                }
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
