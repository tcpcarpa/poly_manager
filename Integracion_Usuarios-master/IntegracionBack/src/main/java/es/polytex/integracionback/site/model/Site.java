package es.polytex.integracionback.site.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
/*[
  {
    "id": 0,
    "name": "string",
    "mode": 0,
    "settings": {
      "additionalProp1": "string",
      "additionalProp2": "string",
      "additionalProp3": "string"
    }
  }
]

	No links
500

*/

@JsonIgnoreProperties(ignoreUnknown = true)public class Site {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("settings")
    private Map<String, Object> settings;
    private List<Object> userList;
    private List<Object> siteArrayList;

    public Site(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("mode") String mode, @JsonProperty("settings")  Map<String, Object> settings) {
        this.id = id;
        this.name = name;
        this.mode = mode;
        this.settings = settings;
    }

    public Site() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Map<String, Object> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, Object> settings) {
        this.settings = settings;
    }

    public List<Object> getUserList() {
        return userList;
    }

    public void setUserList(List<Object> userList) {
        this.userList = userList;
    }

    public List<Object> getSiteArrayList() {
        return siteArrayList;
    }

    public void setSiteArrayList(List<Object> siteArrayList) {
        this.siteArrayList = siteArrayList;
    }

    @Override
    public String toString() {
        return "Site{" +

                "id=" + id +
                ", name='" + name + '\'' +
                ", mode='" + mode + '\'' +
                ", settings=" + settings +
                ", userList=" + userList +
                ", siteArrayList=" + siteArrayList +
                '}';
    }

}
