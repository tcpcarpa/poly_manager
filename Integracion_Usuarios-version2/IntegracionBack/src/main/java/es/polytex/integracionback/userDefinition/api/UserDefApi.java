package es.polytex.integracionback.userDefinition.api;

import es.polytex.integracionback.userDefinition.controller.UserDefController;
import es.polytex.integracionback.core.api.Cors;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/config")
public class UserDefApi extends Cors {
    private final UserDefController controller;
    public UserDefApi() {
        this.controller = UserDefController.getInstance();
    }

    @POST
    @Path("/sendconfig/{siteID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendconfig(@PathParam("siteID")String siteID, String requestData) {
        return controller.sendconfig(siteID, requestData);
    }
}
