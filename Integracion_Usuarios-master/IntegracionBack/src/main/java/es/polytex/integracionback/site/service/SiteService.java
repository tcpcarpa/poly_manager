package es.polytex.integracionback.site.service;

import es.polytex.integracionback.core.service.Service;
import es.polytex.integracionback.core.utils.Constants;
import es.polytex.integracionback.site.model.Site;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SiteService extends Service {
    public static Response getSerAllSites(String bearerToken) throws IOException {

        request = new Request.Builder()
                .url(Constants.API + Constants.SITES)
                .header("Authorization", "Bearer " + bearerToken)
                .get()
                .build();

        return CLIENT.newCall(request).execute();
    }

    public List<String> getIDsites(List<Site> sites) {
        ArrayList<String> iDSites = new ArrayList<>();
        for (Site s : sites) {
            if (s.getId() != 0) {
                iDSites.add(String.valueOf(s.getId()));
            }
        }
            return iDSites;
        }

}
