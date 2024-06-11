package es.polytex.integracionback.importApi.api;

import es.polytex.integracionback.core.api.Cors;
import es.polytex.integracionback.importApi.controller.ImportController;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/import")
public class ImportApi extends Cors {
    private final ImportController controller;
    public ImportApi() {this.controller = ImportController.getInstance();}

    @POST
    @Path("/import/{siteID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginImport(@PathParam("siteID")String siteID, String user) {
        return controller.loginImport(siteID, user);
    }
}
