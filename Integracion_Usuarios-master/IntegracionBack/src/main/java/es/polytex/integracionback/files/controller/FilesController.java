package es.polytex.integracionback.files.controller;


import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.controller.Controller;
import es.polytex.integracionback.core.controller.messages.Msg_Error;
import es.polytex.integracionback.core.controller.messages.Msg_OK;
import es.polytex.integracionback.files.db.FilesDB;
import es.polytex.integracionback.files.mapper.FilesMapper;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;
import java.util.List;

public class FilesController extends Controller {
    private static FilesController controller;
    private final FilesDB filesDB;

    protected Response response;

    public static FilesController getInstance() {
        if (controller == null) {
            controller = new FilesController();
        }
        return controller;
    }

    public FilesController() {
        this.filesDB = new FilesDB();
    }

    public Response uploadFile(InputStream fileInputStream) {
        response = null;
        try {
            List<String[]> todos = FilesMapper.leerCSV(fileInputStream);
            List<Users> config = FilesMapper.procesoDatos(todos);
            FilesMapper.saveListToFile(config, "usuarios");
            List<Users> noValidos = FilesMapper.listaNo(FilesMapper.convertirAUsuarios(todos), config);
            FilesMapper.saveListToCSV(noValidos, "NousuariosValidos.csv");

            if (!config.isEmpty()) {
                if (filesDB.isTablaUsuariosVacia()) {
                    filesDB.insertUsers(config);
                    response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, config);
                } else {
                    List<Users> usersList = filesDB.obtenerTodosLosUsuarios();
                    List<Users> usersList1 = FilesMapper.compararPorFecha(config,usersList);
                    if (!usersList1.isEmpty()) {
                        filesDB.actualizarUsuarios(usersList);
                        response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, noValidos.size());
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                    }
                }
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Response uploadLimit(InputStream fileInputStream) {
        response = null;
        try {
            List<String[]> fileContent = FilesMapper.procesoLimit(fileInputStream);
            if (!fileContent.isEmpty()) {
                if (filesDB.isTablaUsuariosVacia()) {
                    filesDB.insLimit(fileContent);
                } else {
                    List<Users> usersviejos = filesDB.obtenerTodosLosUsuarios();
                    List<Users> userModificado = FilesMapper.compararGrupLimit(fileContent, usersviejos);
                    if (!userModificado.isEmpty()) {
                        filesDB.actualizarUsuarios(userModificado);
                        response = doResponse(Response.Status.OK, true, Msg_OK.SUCCESS_TRANSACTION, userModificado.size());
                    } else {
                        response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
                    }
                }
            } else {
                response = doResponse(Response.Status.BAD_REQUEST, false, Msg_Error.ERROR_ADD_TRANSACTION, null);
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
