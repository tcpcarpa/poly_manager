package es.polytex.integracionback.client.api;

import es.polytex.integracionback.client.controller.ClientController;
import es.polytex.integracionback.core.api.Cors;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/client")
public class ClientApi extends Cors {
    private final ClientController controller;
    public ClientApi() {this.controller = ClientController.getInstance();}

    @POST
    @Path("/preImport")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response preImport(String siteID) {
        return controller.preImport(siteID);
    }

}
