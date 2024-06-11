package es.polytex.integracionback.limit.controller;

import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.limit.manager.LimitManager;
import es.polytex.integracionback.site.model.Site;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;
import java.util.List;

public class LimitController extends Controller {
    private static final LimitManager limitManager = new LimitManager();
    private static LimitController controller;

    public static LimitController getInstance() {
        if (controller == null) {
            controller = new LimitController();
        }
        return controller;
    }

    public Response uploadLimit(String siteID, InputStream inputStream) {
        response = null;
        try {
            List<String[]> listalimpia = limitManager.procesarDatos(inputStream);
            if (!listalimpia.isEmpty()) {
                Site s = limitManager.setLimitGrup(listalimpia, siteID);
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
