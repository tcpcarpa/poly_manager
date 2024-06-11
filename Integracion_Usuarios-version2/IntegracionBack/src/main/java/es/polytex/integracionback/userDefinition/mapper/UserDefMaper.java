package es.polytex.integracionback.userDefinition.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.userDefinition.model.ConfigDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDefMaper extends Mapper {
    public static ConfigDefinition requestConfigDefinition(String requestdata) {
        try {
            return MAPPER.readValue(requestdata, ConfigDefinition.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


















    public static List<Integer> requestLimOrder(String limOrderJson) {
        List<Integer> listaEnteros = new ArrayList<>();

        try {
            JsonNode jsonNode = MAPPER.readTree(limOrderJson);
            JsonNode limOrderNode = jsonNode.get("limOrder");

            if (limOrderNode != null && limOrderNode.isObject()) {
                Iterator<String> fieldNames = limOrderNode.fieldNames();
                while (fieldNames.hasNext()) {
                    String fieldName = fieldNames.next();
                    JsonNode valueNode = limOrderNode.get(fieldName);

                    if (valueNode != null) {
                        listaEnteros.add(valueNode.asInt());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaEnteros;
    }

    public static List<String> requestlimitStr(String limOrderJson) {
        try {
            JsonNode rootNode = MAPPER.readTree(limOrderJson);
            JsonNode limOrderNode = rootNode.get("limOrder");
            List<String> limOrderList = new ArrayList<>();

            Iterator<String> fieldNames = limOrderNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                String value = limOrderNode.get(fieldName).asText();
                limOrderList.add(fieldName + ": " + value);
            }

            return limOrderList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
