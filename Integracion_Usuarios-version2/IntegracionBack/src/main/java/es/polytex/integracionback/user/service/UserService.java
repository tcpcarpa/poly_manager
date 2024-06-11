package es.polytex.integracionback.user.service;

import es.polytex.integracionback.core.service.Service;
import es.polytex.integracionback.core.utils.Constants;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class UserService extends Service {
    public static Response getSerUserLogin(String jsonBody) throws IOException {
        requestBody = RequestBody.create(jsonBody, JSON);
        request = new Request.Builder()
                .url(Constants.API + Constants.LOGIN)
                .post(requestBody)
                .build();
        return CLIENT.newCall(request).execute();
    }
}
