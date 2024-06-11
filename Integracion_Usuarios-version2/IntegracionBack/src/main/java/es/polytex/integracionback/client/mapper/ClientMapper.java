package es.polytex.integracionback.client.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.client.service.ClientService;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.files.model.FileInput;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class ClientMapper extends Mapper {
    public static boolean importFile(String token, String json) {
        try {
            if (token != null && json != null) {
                Response response = ClientService.importFile(token, json);
                return response.isSuccessful();
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






    public static String crearJsonUsuarios(List<Users> userList, String siteId) {
        try {
            FileInput fileEnd = new FileInput();
            fileEnd.setUsers(userList);
            fileEnd.setSiteId(siteId);

            MAPPER.configure(SerializationFeature.INDENT_OUTPUT, false);
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            String json = MAPPER.writeValueAsString(fileEnd);

            return json;

        } catch (IOException e) {
            throw new RuntimeException("Error al crear el JSON de usuarios", e);
        }
    }

}
