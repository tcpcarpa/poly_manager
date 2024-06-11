package es.polytex.integracionback.client.api;

import es.polytex.integracionback.client.controller.ClientController;
import es.polytex.integracionback.user.model.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/client")
public class ClientApi {
    private final ClientController controller;
    public ClientApi() {this.controller = ClientController.getInstance();}

    @POST
    @Path("/import")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginImport(User user) {
        return controller.loginImport(user);
    }


}
