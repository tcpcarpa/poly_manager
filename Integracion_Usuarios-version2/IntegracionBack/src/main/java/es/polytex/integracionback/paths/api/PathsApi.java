package es.polytex.integracionback.paths.api;

import es.polytex.integracionback.paths.controller.PathsController;
import es.polytex.integracionback.core.api.Cors;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/path")

public class PathsApi extends Cors {
    private final PathsController controller;
    public PathsApi() {
        this.controller = PathsController.getInstance();
    }

    @POST
    @Path("/path/{siteID}/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autoPath(@PathParam("siteID") String siteID, String autopath) {
        return controller.autoPath(siteID, autopath);
    }
}
