package es.polytex.integracionback.site.controller;

import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.site.manager.SiteManager;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class SiteController extends Controller {
    private static final SiteManager siteManager = new SiteManager();
    private static SiteController controller;
    public static SiteController getInstance() {
        if (controller == null) {
            controller = new SiteController();
        }
        return controller;
    }

    public Response getUserdefinition(String siteID) {
        try {
            String strID = siteID.replaceAll("\"", "").replaceAll("\\\\", "");
            Site s = siteManager.getSite(strID);
            if (s != null) {
                ConfigDefinition configDefinition = siteManager.getUserDefinition(s);
                if (configDefinition != null) {
                    response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_USERDIFINITION, configDefinition);
                } else {
                    response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.USERDEFINITION_NO_EXISTS, null);
                }
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.SITES_NO_EXISTS, null);
            }
            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Response getSite(Site site) {
        try {
            Site s = siteManager.getSite(site);
            if (s != null) {
                    response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_SITES, s);
                } else {
                    response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.SITES_NO_EXISTS, null);
                }
            return response;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
