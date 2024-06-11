package es.polytex.integracionback.client.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.client.service.ClientService;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.files.model.FileCSV;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClientMapper extends Mapper {
    public static String importFile(String token, String json) throws IOException {
        try {
            if (token != null && json != null) {
                Response response = ClientService.importFile(token, json);
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    String cuerpoRespuesta = responseBody.string();
                    return cuerpoRespuesta;
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Users> cargarListaDesdeArchivo() {
        List<Users> userList = new ArrayList<>();
        try {
            File jsonFile = new File(System.getProperty("java.io.tmpdir"), "UsuariosValidos.json");
            userList = MAPPER.readValue(jsonFile, new TypeReference<List<Users>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return userList;
    }


    public static String crearJsonUsuarios(List<Users> userList, int siteId) {
        try {
            FileCSV fileEnd = new FileCSV();
            fileEnd.setUsers(userList);
            fileEnd.setSiteId(siteId);

            MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            String json = MAPPER.writeValueAsString(fileEnd);

            return json;

        } catch (IOException e) {
            throw new RuntimeException("Error al crear el JSON de usuarios", e);
        }
    }

    public static String crearJsonUsuariosFormato(List<Users> userList, int siteId) {
        try {
            FileCSV fileEnd = new FileCSV();
            fileEnd.setUsers(userList);
            fileEnd.setSiteId(siteId);

            MAPPER.configure(SerializationFeature.INDENT_OUTPUT, false);
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            String json2 = MAPPER.writeValueAsString(fileEnd);

            return json2;

        } catch (IOException e) {
            throw new RuntimeException("Error al crear el JSON de usuarios", e);
        }
    }

    public static int readSiteIdFromFile() throws IOException {
        Path tempPath = Paths.get(System.getProperty("java.io.tmpdir"));
        File siteIdFile = tempPath.resolve("siteId.txt").toFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(siteIdFile))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line.trim());
            } else {
                throw new IOException("El archivo está vacío.");
            }
        }
    }

    public static boolean deleteFileTemp(String filePath) throws IOException {
        Path tempPath = Paths.get(System.getProperty("java.io.tmpdir"));
        File siteIdFile = tempPath.resolve(filePath).toFile();
        return siteIdFile.delete();
    }

}
