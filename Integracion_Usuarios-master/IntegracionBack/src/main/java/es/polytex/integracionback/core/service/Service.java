package es.polytex.integracionback.core.service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.concurrent.TimeUnit;

public abstract class Service {
    protected static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build();
    protected static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    protected static RequestBody requestBody;
    protected static Request request;
}
