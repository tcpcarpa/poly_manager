package es.polytex.integracionback.user.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.user.model.User;
import es.polytex.integracionback.user.service.UserService;
import okhttp3.Response;

import java.io.IOException;


public class UserMapper extends Mapper {
    public static String getToken(User user) throws IOException {
        String jsonBody = MAPPER.writeValueAsString(user);

        Response response = UserService.getSerUserLogin(jsonBody);

        if (response.isSuccessful()) {
            String responseBody = response.body().string();

            JsonNode jsonResponse = MAPPER.readTree(responseBody);

            return jsonResponse.get("access_token").asText();
        } else {
            return null;
        }
    }

}
