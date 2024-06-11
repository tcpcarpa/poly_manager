package es.polytex.integracionback.user.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.user.model.User;
import es.polytex.integracionback.user.service.UserService;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Site> sitesnoDB(List<Site> siteApi, List<Site> siteDb){
        List<Site> nuevasSites = new ArrayList<>();
        for (Site sitedb : siteDb) {
            boolean existe = false;
            for (Site siteUsuario : siteApi) {
                if (sitedb.getSite_id().equals(siteUsuario.getSite_id())) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                nuevasSites.add(sitedb);
            }
        }

        return nuevasSites;
    }
}
