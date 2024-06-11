package es.polytex.integracionback.auto.controller;

import es.polytex.integracionback.auto.manager.AutoManager;
import es.polytex.integracionback.auto.mapper.AutoMapper;
import es.polytex.integracionback.auto.model.Tiempo;
import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.site.model.Site;
import jakarta.ws.rs.core.Response;

public class AutoControler extends Controller {
    private static final AutoManager autoManager = new AutoManager();
    private static AutoControler controller;

    public static AutoControler getInstance() {
        if (controller == null) {
            controller = new AutoControler();
        }
        return controller;
    }

    public Response autoDate(String siteID, String tiempo) {
        response = null;
        try {
            int dia = AutoMapper.getDia(tiempo);
            int horas = AutoMapper.getHora(tiempo);
            int minutos = AutoMapper.getMinuto(tiempo);
            Site s =  autoManager.actualizarTiempo(siteID, new Tiempo(dia, horas, minutos));
            if (s != null) {
                response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, s);
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



