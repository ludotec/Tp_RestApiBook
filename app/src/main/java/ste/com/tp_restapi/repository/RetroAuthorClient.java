package ste.com.tp_restapi.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ste.com.tp_restapi.services.AuthorApiService;

public class RetroAuthorClient {
    private static RetroAuthorClient instance = null;
    private AuthorApiService myApiAuthor;

    private RetroAuthorClient() {
        Retrofit retrofitAuthor = new Retrofit.Builder().baseUrl(AuthorApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApiAuthor = retrofitAuthor.create(AuthorApiService.class);
    }

    public static synchronized RetroAuthorClient getInstance() {
        if (instance == null) {
            instance = new RetroAuthorClient();
        }
        return instance;
    }

    public AuthorApiService getMyApiAuthor() {
        return myApiAuthor;
    }
}
