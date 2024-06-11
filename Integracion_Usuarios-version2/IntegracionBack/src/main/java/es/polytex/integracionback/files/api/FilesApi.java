package es.polytex.integracionback.files.api;

import es.polytex.integracionback.core.api.Cors;
import es.polytex.integracionback.files.controller.FilesController;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;


@Path("/file")
@MultipartConfig
public class FilesApi extends Cors {
    private final FilesController controller;
    public FilesApi() {
        this.controller = FilesController.getInstance();
    }

    @POST
    @Path("/csv/{siteID}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFileCSV( @PathParam("siteID") String siteID, InputStream fileInputStream) {
        return controller.uploadFile(siteID,fileInputStream);
    }

}