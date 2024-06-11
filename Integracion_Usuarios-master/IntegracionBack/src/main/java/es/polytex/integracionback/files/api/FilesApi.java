package es.polytex.integracionback.files.api;

import es.polytex.integracionback.files.controller.FilesController;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;


@Path("/file")
@MultipartConfig
public class FilesApi {
    private final FilesController controller;

    public FilesApi() {
        this.controller = FilesController.getInstance();
    }

    @POST
    @Path("/csv")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFileCSV(InputStream fileInputStream) {
        return controller.uploadFile(fileInputStream);
    }

    @POST
    @Path("/limit")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFileLimit(InputStream fileInputStream) {
        return controller.uploadLimit(fileInputStream);
    }


    @POST
    @Path("/sql")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadSQLFile(InputStream fileInputStream) {
        return null; // uploadFileSQL(fileInputStream, ".sql");
    }



}