package es.polytex.integracionback.site.controller;

import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.service.SiteService;
import jakarta.ws.rs.core.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SiteController extends Controller {
    private static SiteController controller;
    protected SiteService siteService;
    private final SiteDB siteDB;


    public static SiteController getInstance() {
        if (controller == null) {
            controller = new SiteController();
        }
        return controller;
    }

    public SiteController() {
        this.siteService = new SiteService();
        this.siteDB = new SiteDB();
    }

    public Response getSite(String id) {
        //siteDB.insertSite(site);
        //int id = site.getId();
        // String idStr = String.valueOf(id);
        String soloNumero = id.replaceAll("[^0-9]", "");
        int numero = Integer.parseInt(soloNumero);
        if (numero > 0) {
            //saveSiteIdToFile(id);
            response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, numero);
        } else {
            response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
        }
        return response;
    }

    private void saveSiteIdToFile(int siteId) throws IOException {
        Path tempPath = Paths.get(System.getProperty("java.io.tmpdir"));
        File siteIdFile = tempPath.resolve("siteId.txt").toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(siteIdFile))) {
            writer.write(String.valueOf(siteId));
        }
    }

    private void saveDB() {

    }

}
