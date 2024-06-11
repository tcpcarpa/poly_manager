package es.polytex.integracionback.userDefinition.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.user.model.User;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    private String favSiteId;
    @JsonProperty("user")
    private User user;
    @JsonProperty("userDefinition")
    private ConfigDefinition userDefinition;
    @JsonProperty("site")
    private Site site;



    public Config() {
    }

    public  User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public ConfigDefinition getUserDefinition() {
        return userDefinition;
    }

    public void setUserDefinition(ConfigDefinition userDefinition) {
        this.userDefinition = userDefinition;
    }
}


