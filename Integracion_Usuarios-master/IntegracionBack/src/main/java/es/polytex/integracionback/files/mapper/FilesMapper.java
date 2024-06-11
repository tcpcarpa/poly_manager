package es.polytex.integracionback.files.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.mapper.Mapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FilesMapper extends Mapper {

    public static List<Users> procesoDatos(List<String[]> listaDatos) {
        List<String[]> validoID = filtrarAlfanumericos(listaDatos);
        List<String[]> sinUnno = copiarListaSinColumna1(validoID);
        List<String[]> cambio7x6elimino8 = modificarColumnas(sinUnno);
        List<String[]> esHexa = convertirCampo1AHexadecimal(cambio7x6elimino8);
        List<String[]> esFecha = filtrarPorFecha(esHexa);
        List<Users> resulconfig = cumpleConConfig(esFecha);

        return resulconfig;
    }

    public static List<String[]> procesoLimit(InputStream ins) {
        try {
            List<String[]> csv = leerCSV(ins);
            List<String[]> validoID = filtrarAlfanumericos(csv);
            List<String[]> tres = filtrarListStringPorTresCampos(validoID);
            return tres;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        //------------------------------------------------------------------------------------------------------------------
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

    public static List<String[]> leerCsv2(InputStream fileInputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            List<String> primerasNLineas = reader.lines().limit(5).collect(Collectors.toList());
            String delimitador = detectarDelimitador(primerasNLineas);

            return reader.lines()
                    .map(linea -> Arrays.asList(linea.split(Pattern.quote(delimitador))))
                    .filter(campos -> campos.size() > 5)
                    .map(campos -> campos.stream().toArray(String[]::new))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
    }

    private static long contarDelimitadorEnPrimerasLineas(List<String> primerasLineas, String delimitador) {
        return primerasLineas.stream()
                .map(linea -> Arrays.asList(linea.split(Pattern.quote(delimitador))))
                .filter(campos -> campos.size() > 1)
                .count();
    }

    private static String detectarDelimitador(List<String> primerasNLineas) {
        String[] posiblesDelimitadores = {",", ";", "\t", "\\", "|", "/"};
        Map<String, Long> conteos = Arrays.stream(posiblesDelimitadores)
                .collect(Collectors.toMap(
                        delimitador -> delimitador,
                        delimitador -> contarDelimitadorEnPrimerasLineas(primerasNLineas, delimitador)
                ));

        return conteos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(",");
    }


    //------------------------------------------------------------------------------------------------------------------

    private static List<String[]> filtrarAlfanumericos(List<String[]> listaDatos) {
        List<String[]> resultadosFiltrados = new ArrayList<>();

        for (String[] row : listaDatos) {
            if (esAlfanumerico(row[0])) {
                resultadosFiltrados.add(row);
            }
        }

        return resultadosFiltrados;
    }

    private static boolean esAlfanumerico(String cadena) {
        return cadena != null && cadena.matches("^[a-zA-Z0-9]+$");
    }

    //------------------------------------------------------------------------------------------------------------------

    private static List<String[]> copiarListaSinColumna1(List<String[]> listaOriginal) {
        List<String[]> listaCopia = new ArrayList<>();

        for (String[] original : listaOriginal) {
            String[] copia = new String[original.length - 1];

            System.arraycopy(original, 0, copia, 0, 1);
            System.arraycopy(original, 2, copia, 1, original.length - 2);

            listaCopia.add(copia);
        }
        return listaCopia;
    }

    //------------------------------------------------------------------------------------------------------------------

    private static List<String[]> modificarColumnas(List<String[]> listaDatos) {
        List<String[]> listaModificada = new ArrayList<>();

        for (String[] row : listaDatos) {
            if (row.length >= 8) {
                String temp = row[6];
                row[6] = row[7];
                row[7] = temp;

                String[] filaModificada = new String[8];
                System.arraycopy(row, 0, filaModificada, 0, 7);
                System.arraycopy(row, 0, filaModificada, 0, filaModificada.length);
                listaModificada.add(filaModificada);
            } else {
                listaModificada.add(row);
            }
        }

        return listaModificada;
    }

    //------------------------------------------------------------------------------------------------------------------

    private static List<String[]> convertirCampo1AHexadecimal(List<String[]> listaDatos) {
        List<String[]> listaConvertida = new ArrayList<>();

        for (String[] row : listaDatos) {
            if (row[1] != null && !row[1].isEmpty() && esAlfanumerico(row[1]) && esCadenaHexadecimal(row[1])) {
                BigInteger decimal = new BigInteger(row[1], 16);

                String hexConLongitud = String.format("%0" + 10 + "X", decimal);

                String[] filaConvertida = row.clone();
                filaConvertida[1] = hexConLongitud;

                listaConvertida.add(filaConvertida);
            }
        }
        return listaConvertida;
    }

    public static boolean esCadenaHexadecimal(String cadena) {
        try {
            new BigInteger(cadena, 16);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//------------------------------------------------------------------------------------------------------------------

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


//------------------------------------------------------------------------------------------------------------------

    private static List<Users> cumpleConConfig(List<String[]> listaCampos) {
        ObjectMapper mapper = JsonMapper.builder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).build();
        List<Users> usuariosCumplen = new ArrayList<>();

        for (String[] campos : listaCampos) {
            try {
                JsonNode configNode = mapper.readTree(new File(crearArchivoConfig()));

                JsonNode userDefinition = configNode.path("config").path("userDefinition");

                Users usuarioCumpleConConfig = new Users();
                setUserFieldValue(usuarioCumpleConConfig, "userId", campos[userDefinition.get("userId").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "cardId", campos[userDefinition.get("cardId").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "firstName", campos[userDefinition.get("firstName").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "lastName", campos[userDefinition.get("lastName").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "departmentName", campos[userDefinition.get("departmentName").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "title", campos[userDefinition.get("title").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "effectiveLimitGroup", campos[userDefinition.get("effectiveLimitGroup").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "isDisabledOrDisabledDate", campos[userDefinition.get("isDisabledOrDisabledDate").asInt()]);
                setUserFieldValue(usuarioCumpleConConfig, "simpleOrExtendedModeQuantityBalance", "");
                setUserFieldValue(usuarioCumpleConConfig, "rfidModeItemNameGroupSHORTQuantitybalance", "");
                setUserFieldValue(usuarioCumpleConConfig, "cardId2", "");
                setUserFieldValue(usuarioCumpleConConfig, "email", "");


                usuariosCumplen.add(usuarioCumpleConConfig);
            } catch (Exception e) {
                System.err.println("Error al procesar usuario: " + Arrays.toString(campos));
                throw new RuntimeException(e);
            }
        }
        return usuariosCumplen;
    }

    private static void setUserFieldValue(Users user, String fieldName, String value) {
        try {
            Field field = Users.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(user, value);
        } catch (Exception e) {
            throw new RuntimeException("Error al establecer el valor del campo " + fieldName, e);
        }
    }

    private static String crearArchivoConfig() {
        try {
            MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
            JsonNode rootNode = MAPPER.createObjectNode();
            ((ObjectNode) rootNode).putObject("config")
                    .put("user", "")
                    .put("pass", "")
                    .put("separator", "")
                    .put("favSiteId", "")
                    .putObject("userDefinition")
                    .put("userId", 0)
                    .put("cardId", 1)
                    .put("firstName", 2)
                    .put("lastName", 3)
                    .put("departmentName", 4)
                    .put("title", 5)
                    .put("effectiveLimitGroup", 6)
                    .put("isDisabledOrDisabledDate", 7)
                    .put("simpleOrExtendedModeQuantityBalance", "")
                    .put("rfidModeItemNameGroupSHORTQuantitybalance", "")
                    .put("cardId2", "")
                    .put("email", "");

            Path tempPath = Paths.get(System.getProperty("java.io.tmpdir"));
            String configFilePath = tempPath.toString() + "/config.json";
            MAPPER.writeValue(new File(configFilePath), rootNode);
            return configFilePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//------------------------------------------------------------------------------------------------------------------

    public static List<Users> listaNo(List<Users> listaEntera, List<Users> listaFinal) {
        List<Users> diferencias = new ArrayList<>();
        for (Users u : listaEntera) {
            if (!listaFinal.contains(u)) {
                diferencias.add(u);
            }
        }
        return diferencias;
    }

    public static List<Users> convertirAUsuarios(List<String[]> listaDatos) {
        List<Users> listaUsuarios = new ArrayList<>();

        for (String[] datos : listaDatos) {
            if (esAlfanumerico(datos[0])) {
                Users user = new Users();
                user.setuserId(datos[0]);
                user.setCardId(datos[1]);
                user.setFirstName(datos[2]);
                user.setLastName(datos[3]);
                user.setDepartmentName(datos[4]);
                user.setTitle(datos[5]);
                user.setIsDisabledOrDisabledDate(datos[6]);
                user.setEffectiveLimitGroup(datos[7]);
                user.setSimpleOrExtendedModeQuantityBalance("");
                user.setRfidModeItemNameGroupSHORTQuantitybalance("");
                user.setCardId2("");
                user.setEmail("");

                listaUsuarios.add(user);
            }
        }

        return listaUsuarios;
    }

//------------------------------------------------------------------------------------------------------------------

    public static boolean saveListToFile(List<Users> userList, String name) {
        Path tempPath = Paths.get(System.getProperty("java.io.tmpdir"));

        File outputFile = new File(tempPath.toFile(), name);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonUsers = MAPPER.writeValueAsString(userList);

            writer.write(jsonUsers);

            return true;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar en formato JSON.", e);
        }
    }

// SAVE LIST TO BD

    public static boolean saveListToCSV(List<Users> userList, String name) {
        Path tempPath = Paths.get(System.getProperty("java.io.tmpdir"));
        File outputFile = new File(tempPath.toFile(), name);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("userId,cardId,firstName,lastName,departmentName,title,effectiveLimitGroup,isDisabledOrDisabledDate");
            writer.newLine();

            for (Users user : userList) {
                StringJoiner csvLine = new StringJoiner(",");
                csvLine.add(user.getUserId());
                csvLine.add(user.getCardId());
                csvLine.add(user.getFirstName());
                csvLine.add(user.getLastName());
                csvLine.add(user.getDepartmentName());
                csvLine.add(user.getTitle());
                csvLine.add(user.getEffectiveLimitGroup());
                csvLine.add(user.getIsDisabledOrDisabledDate());

                writer.write(csvLine.toString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar en formato CSV.", e);
        }
    }

//------------------------------------------------------------------------------------------------------------------

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Users> compararPorFecha(List<Users> lista1, List<Users> lista2) {
        List<Users> usuariosPosteriores = new ArrayList<>();

        for (Users usuario1 : lista1) {
            for (Users usuario2 : lista2) {
                if (usuario1.getUserId().equals(usuario2.getUserId())) {
                    if (esNumeroConGuiones(usuario1.getIsDisabledOrDisabledDate()) &&
                    esNumeroConGuiones(usuario1.getIsDisabledOrDisabledDate())) {
                        if (compararFechas(usuario1.getIsDisabledOrDisabledDate(), usuario2.getIsDisabledOrDisabledDate())) {
                            usuariosPosteriores.add(usuario1);
                        }
                    }
                }
            }
        }
        return usuariosPosteriores;
    }

    private static boolean compararFechas(String fecha1, String fecha2) {
        try {
            Date date1 = dateFormat.parse(fecha1);
            Date date2 = dateFormat.parse(fecha2);
            return date1.compareTo(date2) <= 0;
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    private static boolean esNumeroConGuiones(String input) {
        String regex = "^[0-9\\-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    //------------------------------------------------------------------------------------------------------------------

    public static List<String[]> filtrarListStringPorTresCampos(List<String[]> listaDatos) {
        List<String[]> resultadosFiltrados = new ArrayList<>();

        for (String[] fila : listaDatos) {
            if (fila.length == 3) {
                resultadosFiltrados.add(fila);
            }
        }

        return resultadosFiltrados;
    }

    public static List<Users> compararGrupLimit(List<String[]> listaStrings, List<Users> listaUsuarios) {
        for (String[] datosString : listaStrings) {
            for (Users usuario : listaUsuarios) {
                if (usuario.getDepartmentName().equals(datosString[0]) && usuario.getTitle().equals(datosString[1])) {
                    usuario.setEffectiveLimitGroup(datosString[2]);
                }
            }
        }
        return listaUsuarios;
    }

}





