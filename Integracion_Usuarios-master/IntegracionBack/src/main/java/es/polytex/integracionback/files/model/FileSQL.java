package es.polytex.integracionback.files.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.ws.rs.FormParam;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FileSQL extends FileInput {
    @FormParam("query")
    private String query;


    public FileSQL() {
    }

    public FileSQL(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
