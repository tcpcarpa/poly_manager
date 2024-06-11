package es.polytex.integracionback.client.service;

import es.polytex.integracionback.core.service.Service;
import es.polytex.integracionback.core.utils.Constants;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class ClientService extends Service {

    public static  Response importFile (String bearerToken, String jsonBody) throws IOException {
        requestBody = RequestBody.create(jsonBody, JSON);

        request = new Request.Builder()
                .url(Constants.API + Constants.IMPORT)
                .header("Authorization", "Bearer " + bearerToken)
                .post(requestBody)
                .build();

        return CLIENT.newCall(request).execute();
    }



}
