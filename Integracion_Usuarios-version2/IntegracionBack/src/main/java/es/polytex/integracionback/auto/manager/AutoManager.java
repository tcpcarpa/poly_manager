package es.polytex.integracionback.auto.manager;

import es.polytex.integracionback.auto.model.Tiempo;
import es.polytex.integracionback.auto.service.AutoService;
import es.polytex.integracionback.core.manager.Manager;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.model.Site;

import java.sql.SQLException;

public class AutoManager extends Manager {
    private final SiteDB siteDB;
    private final FilesDB filesDB;
    private final AutoService autoService;

    public AutoManager() {
        this.siteDB = new SiteDB();
        this.filesDB = new FilesDB();
        this.autoService = new AutoService();
    }
    public Site actualizarTiempo(String siteID, Tiempo tiempo) throws SQLException {
        Site s = siteDB.getSiteById(siteID);
        tiempo.setDia(tiempo.getDia());
        tiempo.setHora(tiempo.getHora());
        tiempo.setMinuto(tiempo.getMinuto());
        s.setFecha_update(tiempo.getDia() + "Ds " + tiempo.getHora() + "Hs y " + tiempo.getMinuto() + "Ms");
        filesDB.updateSite(s);
        autoService.programarTarea(tiempo.getDia(), tiempo.getHora(), tiempo.getMinuto(), siteID);
        return s;
    }



}
