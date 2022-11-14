package ste.com.tp_restapi.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ste.com.tp_restapi.services.ApiService;

public class RetroClient {
    private static RetroClient instance = null;
    private ApiService myApi;

    private RetroClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiService.class);
    }

    public static synchronized RetroClient getInstance() {
        if (instance == null) {
            instance = new RetroClient();
        }
        return instance;
    }

    public ApiService getMyApi() {
        return myApi;
    }
}
