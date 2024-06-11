package es.polytex.integracionback.core.utils;

import java.text.SimpleDateFormat;

public class Constants {

/*
https://external-api.polytex.cloud/api/v1/Identity/login
https://external-api.polytex.cloud/api/v1/Site/getAll
https://external-api.polytex.cloud/api/v1/User/Import
 */

 public static final String API = "https://external-api.polytex.cloud/api/v1";
 public static final String INT = "http://localhost:8080/IntegracionBack-1.0-SNAPSHOT/api/v1/";

 public static final String CONFIG = "/home/toni-polytex/Documentos/GitHub/Integracion_Usuarios/IntegracionBack/src/main/java/es/polytex/integracionback/abs/utils/config.json";
 public static final String LISTUSERS = "/home/toni-polytex/Documentos/GitHub/Integracion_Usuarios/IntegracionBack/src/main/java/es/polytex/integracionback/abs/utils/usuariosValidos.json";
 public static final String FILESITE = "/home/toni-polytex/Documentos/GitHub/Integracion_Usuarios/IntegracionBack/src/main/java/es/polytex/integracionback/core/utils/siteId.txt";
 public static final String FILEJSON = "/home/toni-polytex/Documentos/GitHub/Integracion_Usuarios/IntegracionBack/src/main/java/es/polytex/integracionback/core/utils/";

 public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

 public static final String SITES = "/Site/getAll";
 public static final String IMPORT = "/User/import";
 public static final String LOGIN = "/Identity/login";

}
