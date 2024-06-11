package es.polytex.integracionback.userDefinition.controller;

import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.userDefinition.manager.UserDefManager;
import es.polytex.integracionback.userDefinition.mapper.UserDefMaper;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;
import jakarta.ws.rs.core.Response;

public class UserDefController extends Controller {
    private static final UserDefManager userDefManager = new UserDefManager();
    private static UserDefController controller;
    public static UserDefController getInstance() {
        if (controller == null) {
            controller = new UserDefController();
        }
        return controller;
    }

    public Response sendconfig(String siteID, String requestData) {
        response = null;
        try {
            ConfigDefinition configDefinition = UserDefMaper.requestConfigDefinition(requestData);
            ConfigDefinition userDefinition = userDefManager.cogervaloresConfig(configDefinition, siteID);
            if (userDefManager.setUserDef(userDefinition, siteID)) {
                response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_USERDIFINITION, userDefinition);
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.USERDEFINITION_NO_EXISTS, null);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
