package es.polytex.integracionback.paths.controller;

import es.polytex.integracionback.paths.manager.PathsManager;
import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.site.model.Site;
import jakarta.ws.rs.core.Response;

public class PathsController extends Controller {
    private static final PathsManager pathsManager = new PathsManager();
    private static PathsController controller;

    public static PathsController getInstance() {
        if (controller == null) {
            controller = new PathsController();
        }
        return controller;
    }

    public Response autoPath(String siteID, String update) {
        response = null;
        try {
            Site s = pathsManager.setPaths(siteID, update);
            if (s != null) {
                response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, s);
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
