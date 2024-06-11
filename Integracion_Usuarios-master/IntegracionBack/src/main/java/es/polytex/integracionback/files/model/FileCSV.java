package es.polytex.integracionback.files.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.polytex.integracionback.client.model.Users;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FileCSV extends FileInput {

    @JsonProperty("users")
    private List<Users> users;

    @JsonProperty("siteId")
    private int siteId;


    public FileCSV() {
    }

    public FileCSV(@JsonProperty("users") List<Users> users, @JsonProperty("siteId") int siteId) {
        this.users = users;
        this.siteId = siteId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
