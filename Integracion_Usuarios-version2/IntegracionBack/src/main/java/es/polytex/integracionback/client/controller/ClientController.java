package es.polytex.integracionback.client.controller;

import es.polytex.integracionback.client.manager.ClientManager;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class ClientController extends Controller {
    private static final ClientManager clientManager = new ClientManager();
    private static ClientController controller;

    public static ClientController getInstance() {
        if (controller == null) {
            controller = new ClientController();
        }
        return controller;
    }

    public Response preImport(String id) {
        response = null;
        try {
            String strID = id.replaceAll("\"", "").replaceAll("\\\\", "");
            List<String[]> listlimit = clientManager.getLimit(strID);
            if (!listlimit.isEmpty()) {
                List<Users> listIni = clientManager.getAllUsers(strID);
                if (!listIni.isEmpty()) {
                    List<Users> listfinal = clientManager.updateUsersEffectiveLimitGroup(listIni, listlimit, strID);
                    if (!listfinal.isEmpty()) {
                        List<Users> listUnica = clientManager.removeDuplicateUsers(listfinal);
                        if (!listUnica.isEmpty()) {
                            if (clientManager.insaAll(listfinal, strID)) {
                                response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, listfinal.size());
                            } else {
                                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                            }
                        } else {
                            response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_PERMISION, null);
                        }
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_PERMISION, null);
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