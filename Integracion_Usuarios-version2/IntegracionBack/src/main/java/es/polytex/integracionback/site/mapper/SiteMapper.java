package es.polytex.integracionback.site.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import es.polytex.integracionback.core.mapper.Mapper;
import es.polytex.integracionback.site.model.Site;
import es.polytex.integracionback.site.service.SiteService;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class SiteMapper extends Mapper {
    public static String getSites(String token) throws IOException {
        if (token != null) {
            Response response = SiteService.getSerAllSites(token);
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return responseBody;
            }
        }
        return null;
    }

    public static List<Site> getSiteLIST(String jsonBody) throws IOException {
        List<Site> siteList = MAPPER.readValue(jsonBody, new TypeReference<List<Site>>() {
        });
        return siteList;

    }





}









