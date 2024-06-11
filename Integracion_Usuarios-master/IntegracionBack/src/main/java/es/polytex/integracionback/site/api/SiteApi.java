package es.polytex.integracionback.site.api;

import es.polytex.integracionback.site.controller.SiteController;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/site")
public class SiteApi {
    private final SiteController controller;

    public SiteApi() {
        this.controller = SiteController.getInstance();
    }

    @POST
    @Path("/siteID")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSiteID(String id) {
        return controller.getSite(id);

    }


}
