package es.polytex.integracionback.user.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.polytex.integracionback.site.model.Site;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)public class User {
    private static int contador = 0;
    @JsonProperty("user_id")
    private String user_id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("numSites")
    private int numSites;
    private List<Site> siteList;

    @JsonCreator
    public User() {
    }

    public User(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }


    public User(String username, String password, List<Site> siteList, int numSites) {
        this.user_id =  String.valueOf(++contador);;
        this.username = username;
        this.password = password;
        this.siteList = siteList;
        this.numSites = numSites;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getNumSites() {
        return numSites;
    }

    public void setNumSites(int numSites) {
        this.numSites = numSites;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", numSites=" + numSites +
                ", siteList=" + siteList +
                '}';
    }
}
