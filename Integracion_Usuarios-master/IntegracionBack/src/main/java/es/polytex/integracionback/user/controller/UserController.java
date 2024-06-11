package es.polytex.integracionback.user.controller;

import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.site.mapper.SiteMapper;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.site.service.SiteService;
import es.polytex.integracionback.user.mapper.UserMapper;
import es.polytex.integracionback.user.model.User;
import es.polytex.integracionback.user.service.UserService;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

public class UserController extends Controller {
    private static UserController controller;
    protected UserService userService;
    public SiteService siteService;


    public static UserController getInstance() {
        if (controller == null) {
            controller = new UserController();
        }
        return controller;
    }

    public UserController() {
        this.siteService = new SiteService();
        this.userService = new UserService();
    }

    public Response loginUser(User user) {
        response = null;
        try {
            String token = UserMapper.getToken(user);
            if (token != null) {
                String sitesSTR = SiteMapper.getSites(UserMapper.getToken(user));
                if (sitesSTR != null) {
                    List<Site> sites = SiteMapper.getSiteLIST(sitesSTR);
                    if (sites != null && !sites.isEmpty()) {
                        List<String> sitesID = siteService.getIDsites(sites);
                        if (sitesID != null && !sitesID.isEmpty()) {
                            response = doResponse(Response.Status.OK, true, Msg_OK.USER_EXIST, sitesID);
                        } else {
                            response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.USER_NO_EXIST, null);
                        }
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.USER_NO_EXIST, null);
                    }
                } else {
                    response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.USER_NO_EXIST, null);
                }
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.USER_NO_EXIST, null);
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}