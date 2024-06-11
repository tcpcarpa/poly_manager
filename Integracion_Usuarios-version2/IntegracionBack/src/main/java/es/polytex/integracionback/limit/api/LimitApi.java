package es.polytex.integracionback.limit.api;

import es.polytex.integracionback.core.api.Cors;
import es.polytex.integracionback.limit.controller.LimitController;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;
@Path("/limit")
@MultipartConfig
public class LimitApi extends Cors {
    private final LimitController controller;

    public LimitApi() {
        this.controller = LimitController.getInstance();
    }
    @POST
    @Path("/limit/{siteID}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadLimit(@PathParam("siteID") String siteID, InputStream fileInputStream){
        return controller.uploadLimit(siteID,fileInputStream);
    }
}
