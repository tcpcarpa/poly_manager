package es.polytex.integracionback.paths.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import es.polytex.integracionback.core.mapper.Mapper;

public class PathsMapper extends Mapper {
    public static  String extraerParamPathUsers(String jsonresult) throws JsonProcessingException {
        JsonNode rootNode = MAPPER.readTree(jsonresult);
        String r = "";
        String txt = rootNode.path("pathU").asText();
        if(txt.equals("")){
            r = "Sin Directorio";
        } else{
            r = txt;
        }
        return r;
    }

    public static String extraerParamPathLimit(String jsonresult) throws JsonProcessingException {
        JsonNode rootNode = MAPPER.readTree(jsonresult);
        String r = "";
        String txt = rootNode.path("pathL").asText();
        if(txt.equals("")){
            r = "Sin Directorio";
        } else{
            r = txt;
        }
        return r;
    }
}
