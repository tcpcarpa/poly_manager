package es.polytex.integracionback.user.api;

import es.polytex.integracionback.user.controller.UserController;
import es.polytex.integracionback.user.model.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserApi {
    private final UserController controller;

    public UserApi() {
        this.controller = UserController.getInstance();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) { return controller.loginUser(user);
    }
}



