package es.polytex.integracionback.site.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {
    @JsonProperty("site_id")
    private String site_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("userDefinition")
    private String userDefinition;
    @JsonProperty("numUsers")
    private String numUsers;
    @JsonProperty("lastUpdate")
    private String lastUpdate;
    @JsonProperty("laslimit")
    private String laslimit;
    @JsonProperty("last_import")
    private String last_import;
    @JsonProperty("path_limitGrup")
    private String path_limitGrup;
    @JsonProperty("path_usuarios")
    private String path_usuarios;
    @JsonProperty("fecha_update")
    private String fecha_update;


    @JsonCreator
    public Site() {
    }

    public Site(String site_id, String name, String mode, String userDefinition, String numUsers, String lastUpdate, String laslimit, String last_import, String path_limitGrup, String path_usuarios, String fecha_update) {
        this.site_id = site_id;
        this.name = name;
        this.mode = mode;
        this.userDefinition = userDefinition;
        this.numUsers = numUsers;
        this.lastUpdate = lastUpdate;
        this.laslimit = laslimit;
        this.last_import = last_import;
        this.path_limitGrup = path_limitGrup;
        this.path_usuarios = path_usuarios;
        this.fecha_update = fecha_update;
    }

    public String getId() {
        return site_id;
    }
    public void setId(String id) {
        this.site_id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getSite_id() {
        return site_id;
    }
    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }
    public String getUserDefinition() {
        return userDefinition;
    }
    public void setUserDefinition(String userDefinition) {
        this.userDefinition = userDefinition;
    }
    public String getNumUsers() {
        return numUsers;
    }
    public void setNumUsers(String numUsers) {
        this.numUsers = numUsers;
    }
    public String getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public String getLaslimit() {
        return laslimit;
    }
    public void setLaslimit(String laslimit) {
        this.laslimit = laslimit;
    }
    public String getPath_limitGrup() {
        return path_limitGrup;
    }
    public void setPath_limitGrup(String path_limitGrup) {
        this.path_limitGrup = path_limitGrup;
    }
    public String getPath_usuarios() {
        return path_usuarios;
    }
    public String getLast_import() {
        return last_import;
    }
    public void setLast_import(String last_import) {
        this.last_import = last_import;
    }
    public void setPath_usuarios(String path_usuarios) {
        this.path_usuarios = path_usuarios;
    }
    public String getFecha_update() {return fecha_update;}
    public void setFecha_update(String fecha_update) {this.fecha_update = fecha_update;}

    @Override
    public String toString() {
        return "Site{" +
                "site_id='" + site_id + '\'' +
                ", name='" + name + '\'' +
                ", mode='" + mode + '\'' +
                ", userDefinition='" + userDefinition + '\'' +
                ", numUsers='" + numUsers + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", laslimit='" + laslimit + '\'' +
                ", last_import='" + last_import + '\'' +
                ", path_limitGrup='" + path_limitGrup + '\'' +
                ", path_usuarios='" + path_usuarios + '\'' +
                ", fecha_update='" + fecha_update + '\'' +
                '}';
    }

}
