package es.polytex.integracionback.core.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class Mapper {
    protected static final ObjectMapper MAPPER = new ObjectMapper();

    protected static Object responseModeler(okhttp3.Response httpResponse, Class<?> classToModel) throws IOException {
        Object response = null;

        if (httpResponse.code() == 200) {
            response = MAPPER.readValue(httpResponse.body().string(), classToModel);
        }

        return response;
    }

    public static String responseModeler(Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }
    public static String responseModeler(okhttp3.Response httpResponse) throws IOException {
        String response = null;

        if (httpResponse.code() == 200) {
            response = httpResponse.body().string();
        }

        return response;
    }
}
