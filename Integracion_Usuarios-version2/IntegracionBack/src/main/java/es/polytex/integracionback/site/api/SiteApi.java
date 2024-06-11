package es.polytex.integracionback.site.api;

import es.polytex.integracionback.core.api.Cors;
import es.polytex.integracionback.site.controller.SiteController;
import es.polytex.integracionback.site.model.Site;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/site")

public class SiteApi extends Cors {
    private final SiteController controller;
    public SiteApi() {
        this.controller = SiteController.getInstance();
    }

    @POST
    @Path("/userDef")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserdefinition(String siteID) {
        return controller.getUserdefinition(siteID);

    }
    @POST
    @Path("/site")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSite(Site site) {
        return controller.getSite(site);

    }
}
