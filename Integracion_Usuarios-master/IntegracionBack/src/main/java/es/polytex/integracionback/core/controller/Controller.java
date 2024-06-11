package es.polytex.integracionback.core.controller;

import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

public abstract class Controller {
    protected Response response;

    protected static Response doResponse(Response.Status status, boolean success, String message, Object object) {
        return Response
                .status(status)
                .entity(doCustomJSON(success, status.getStatusCode(), message, object).toString())
                .build();
    }
    protected static JSONObject doCustomJSON(boolean success, int code, String message, Object request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", success);
        jsonObject.put("code", code);
        jsonObject.put("message", message);

        JSONObject jsonData = new JSONObject();
        jsonData.put("request", request);

        jsonObject.put("data", jsonData);

        return jsonObject;
    }
}
