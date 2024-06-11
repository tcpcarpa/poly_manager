package es.polytex.integracionback.files.mapper;

import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FilesMapper extends Mapper {
    public static List<String[]> leerCSV(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().withTrim();
        try (CSVParser parser = csvFormat.parse(reader)) {
            List<String[]> listaDatos = new ArrayList<>();

            for (CSVRecord record : parser) {
                String[] datos = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    datos[i] = record.get(i);
                }
                listaDatos.add(datos);
            }
            return listaDatos;
        }
    }

    public static List<String[]> solosUsuarios(List<String[]> listaOriginal) {
        List<String[]> listaAlfanumerica = new ArrayList<>();

        for (String[] fila : listaOriginal) {
            if (isAlphanumeric(fila[0]) && isAlphanumeric(fila[1])) {
                listaAlfanumerica.add(fila);
            }
        }
        return listaAlfanumerica;
    }

    private static boolean isAlphanumeric(String value) {
        return value.matches("[a-zA-Z0-9]+");
    }

    //------------------------------------------------------------------------------------------------------------------
    public static List<Users> mapToUsers(List<String[]> data, ConfigDefinition userDefinition, String siteID) {
        List<Users> users = new ArrayList<>();

        for (String[] row : data) {
            Users user = new Users();
            user.setuserId(getStringValue(row, userDefinition.getUserId() - 1));
            user.setCardId(getStringValue(row, userDefinition.getCardId() - 1));
            user.setFirstName(getStringValue(row, userDefinition.getFirstName() - 1));
            user.setLastName(getStringValue(row, userDefinition.getLastName() - 1));
            user.setFullName(getStringValue(row, userDefinition.getFullName() - 1));
            user.setDepartmentName(getStringValue(row, userDefinition.getDepartment() - 1));
            user.setTitle(getStringValue(row, userDefinition.getTitle() - 1));
            user.setEffectiveLimitGroup(getStringValue(row, userDefinition.getLimit() - 1));
            user.setFutureInactive(getStringValue(row, userDefinition.getEnddate() - 1));
            user.setCardId2(getStringValue(row, userDefinition.getCardId2() - 1));
            user.setEmail(getStringValue(row, userDefinition.getEmail() - 1));
            user.setSite_id(siteID);

            users.add(user);
        }
        return users;
    }

    public static String getStringValue(String[] row, int index) {
        if (index >= 0 && index < row.length && row[index] != null) {
            return row[index];
        } else {
            return "";
        }
    }
}





