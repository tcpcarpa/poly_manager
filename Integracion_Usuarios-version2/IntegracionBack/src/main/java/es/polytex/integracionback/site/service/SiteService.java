package es.polytex.integracionback.site.service;

import es.polytex.integracionback.core.service.Service;
import es.polytex.integracionback.core.utils.Constants;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SiteService extends Service {
    public static Response getSerAllSites(String bearerToken) throws IOException {

        request = new Request.Builder()
                .url(Constants.API + Constants.SITES)
                .header("Authorization", "Bearer " + bearerToken)
                .get()
                .build();

        return CLIENT.newCall(request).execute();
    }
}
