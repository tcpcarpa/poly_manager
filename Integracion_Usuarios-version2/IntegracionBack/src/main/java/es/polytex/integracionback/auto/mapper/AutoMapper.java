package es.polytex.integracionback.auto.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import es.polytex.integracionback.client.model.Users;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.files.manager.FileManager;
import es.polytex.integracionback.limit.manager.LimitManager;
import es.polytex.integracionback.site.db.SiteDB;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;
import jakarta.transaction.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AutoMapper extends Mapper {
    private final FileManager fileManager;
    private  final LimitManager limitManager;
    private  final SiteDB siteDB;

    public AutoMapper( ) {
        this.fileManager = new FileManager();
        this.limitManager = new LimitManager();
        this.siteDB = new SiteDB();
    }

    public boolean executeFiles(String siteID) {
        try {
            usuariosCarpeta(siteID);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean executeLimit(String siteID) {
        try {
            limitCarpeta(siteID);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    @Transactional
    public void usuariosCarpeta(String siteID) throws IOException, SQLException {
        Site site = siteDB.getSiteById(siteID);
        if (site != null && site.getPath_usuarios() != null) {
            File carpetaUsuarios = new File(site.getPath_usuarios());
            if (!carpetaUsuarios.exists() && carpetaUsuarios.isDirectory()) {
                if (carpetaUsuarios.listFiles().length != 0) {
                    seleccionFile(siteID, carpetaUsuarios.getPath());
                }
            }
        } else {
            File carpetaEscritorio = new File(System.getProperty("user.home") + "/Desktop/Usuarios");
            carpetaEscritorio.mkdirs();
        }
    }

    @Transactional
    public void limitCarpeta(String siteID) throws IOException, SQLException {
        Site site = siteDB.getSiteById(siteID);
        if (site != null && site.getPath_usuarios() != null) {
            File carpetaLimit = new File(site.getPath_limitGrup());
            if (!carpetaLimit.exists() && carpetaLimit.isDirectory()) {
                if (carpetaLimit.listFiles().length != 0) {
                    seleccionLImit(siteID, carpetaLimit.getPath());
                }
            }
        } else {
            File carpetaEscritorio = new File(System.getProperty("user.home") + "/Desktop/LimitGrup");
            carpetaEscritorio.mkdirs();
        }
    }

    public boolean seleccionFile(String siteID, String folderPath) throws IOException {
        boolean archivosProcesados = false;
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && !file.getName().contains("OK")) {
                    if (processFile(file, siteID)) {
                        nuevoNombre(file, folderPath);
                        archivosProcesados = true;
                    }
                }
            }
        }
        return archivosProcesados;
    }
    public boolean seleccionLImit(String siteID, String folderPath) throws IOException {
        boolean archivosProcesados = false;
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && !file.getName().contains("OK")) {
                    if (processLimit(file, siteID)) {
                        nuevoNombre(file, folderPath);
                        archivosProcesados = true;
                    }
                }
            }
        }
        return archivosProcesados;
    }

    private boolean processFile(File file, String siteID) throws IOException {
        boolean success = false;
        try {
            List<String[]> lista = fileManager.uploadFile(new FileInputStream(file));
            if (!lista.isEmpty()) {
                ConfigDefinition userDefinition = fileManager.getUserdefiniton(siteID);
                if (userDefinition != null) {
                    List<Users> listaUsersEnd = fileManager.getUsersEnd(lista, userDefinition, siteID);
                    fileManager.setUsers(listaUsersEnd, siteID);
                    success = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    private boolean processLimit(File file, String siteID) throws IOException {
        boolean success = false;
        try {
            List<String[]> lista = limitManager.procesarDatos(new FileInputStream(file));
            if (!lista.isEmpty()) {
                Site s = limitManager.setLimitGrup(lista, siteID);
                if (s != null) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    private File nuevoNombre(File file, String destinationFolderPath) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_hh.mma");
        String currentDate = dateFormat.format(new Date());

        String originalName = file.getName();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String newName = originalName.replace(extension, "") + "OK" + currentDate + extension;

        return new File(destinationFolderPath, newName);
    }

    public static int getDia(String tiempo) throws JsonProcessingException {
        JsonNode rootNode = MAPPER.readTree(tiempo);
        String r = "";
        String txt = rootNode.path("dia").asText();
        if(txt.equals("")){
            r = "0";
        } else {
            r = txt;
        }
        return Integer.parseInt(r);
    }
    public static int getHora(String tiempo) throws JsonProcessingException {
        JsonNode rootNode = MAPPER.readTree(tiempo);
        String r = "";
        String txt = rootNode.path("hora").asText();
        if(txt.isEmpty()){
            r = "0";
        } else {
            r = txt;
        }
        return Integer.parseInt(r);    }
    public static int getMinuto(String tiempo) throws JsonProcessingException {
        JsonNode rootNode = MAPPER.readTree(tiempo);
        String r = "";
        String txt = rootNode.path("minuto").asText();
        if(txt.isEmpty()){
            r = "0";
        } else {
            r = txt;
        }
        return Integer.parseInt(txt);
    }
}
