package es.polytex.integracionback.importApi.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.user.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportMapper extends Mapper {
    public static User getUser(String u) {
        try {
            JsonNode jsonNode = MAPPER.readTree(u);
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();
            User user = new User(username, password);
            return user;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String[]> filtrarPorFecha(List<String[]> listaDatos) {
        List<String[]> resultadosFiltrados = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaHoy = new Date();

        for (String[] row : listaDatos) {
            if (esFechaAnterior(row[7], fechaHoy, dateFormat)) {
                resultadosFiltrados.add(row);
            }
        }
        return resultadosFiltrados;
    }

    private static boolean esFechaAnterior(String fechaString, Date fechaHoy, SimpleDateFormat dateFormat) {
        try {
            Date fechaUsuario = dateFormat.parse(fechaString);
            return fechaUsuario.before(fechaHoy);
        } catch (ParseException e) {
            throw new RuntimeException("Error", e);
        }
    }

    private static boolean esFechaPosteriorr(String fechaString, Date fechaHoy, SimpleDateFormat dateFormat) {
        try {
            Date fechaUsuario = dateFormat.parse(fechaString);
            return fechaUsuario.after(fechaHoy);
        } catch (ParseException e) {
            throw new RuntimeException("Error", e);
        }
    }
}
