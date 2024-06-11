package es.polytex.integracionback.auto.api;

import es.polytex.integracionback.auto.controller.AutoControler;
import es.polytex.integracionback.core.api.Cors;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auto")
public class AutoApi extends Cors {
    private final AutoControler controller;
    public AutoApi() {this.controller = AutoControler.getInstance();}

    @POST
    @Path("/tiempo/{siteID}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response autoDate(@PathParam("siteID") String siteID, String tiempo) {
        return controller.autoDate(siteID, tiempo);
    }
}
