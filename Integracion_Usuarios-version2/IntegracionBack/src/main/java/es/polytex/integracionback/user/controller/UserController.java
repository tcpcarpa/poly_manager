package es.polytex.integracionback.user.controller;

import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.user.manager.UserManager;
import es.polytex.integracionback.user.model.User;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

public class UserController extends Controller {
    private static final UserManager userManager = new UserManager();
    private static UserController controller;
    public static UserController getInstance() {
        if (controller == null) {
            controller = new UserController();
        }
        return controller;
    }

    public Response loginUser(User user) {
        response = null;
        try {
            if (userManager.validar(user)) {
                List<Site> sites = userManager.getSites(user);
                if (!sites.isEmpty()) {
                    User u = userManager.getUser(user, sites);
                    if (u != null) {
                        response = doResponse(Response.Status.OK, true, Msg_OK.USER_EXIST, sites);
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.USER_NO_EXIST, null);
                    }
                } else {
                    response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.SITES_EMPTY, null);
                }
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.NO_INET_CONECT, null);
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}