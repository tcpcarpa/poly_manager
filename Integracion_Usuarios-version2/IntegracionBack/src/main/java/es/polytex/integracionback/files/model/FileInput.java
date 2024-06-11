package es.polytex.integracionback.files.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.polytex.integracionback.client.model.Users;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FileInput {
    @JsonProperty("users")
    private List<Users> users;
    @JsonProperty("siteId")
    private String siteId;


    public FileInput() {
    }

    public FileInput(@JsonProperty("users") List<Users> users, @JsonProperty("siteId") String siteId) {
        this.users = users;
        this.siteId = siteId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
