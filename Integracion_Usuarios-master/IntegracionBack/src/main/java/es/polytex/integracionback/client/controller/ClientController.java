package es.polytex.integracionback.client.controller;

import es.polytex.integracionback.client.mapper.ClientMapper;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.user.mapper.UserMapper;
import es.polytex.integracionback.user.model.User;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

public class ClientController extends Controller {
    private static ClientController controller;
    private final FilesDB filesDB;

    protected Response response;


    public static ClientController getInstance() {
        if (controller == null) {
            controller = new ClientController();
        }
        return controller;
    }

    public  ClientController(){
        this.filesDB = new FilesDB();

    }
    public Response loginImport(User user) {
        response = null;
        try {
            String token = UserMapper.getToken(user);
            if (token != null) {
                List<Users> usersList =  filesDB.obtenerTodosLosUsuarios();
                String jsonResult = ClientMapper.crearJsonUsuarios(usersList, ClientMapper.readSiteIdFromFile());
                if (!jsonResult.isEmpty()) {
                    String ok = ClientMapper.importFile(token, jsonResult);
                    if (ok != null) {
                        response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, usersList.size());
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                    }
                } else {
                    response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                }
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
            }
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}