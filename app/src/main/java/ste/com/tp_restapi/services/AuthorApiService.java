package ste.com.tp_restapi.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ste.com.tp_restapi.models.AuthorModel;

public interface AuthorApiService {

    String BASE_URL = "https://apiescritores.herokuapp.com/api/v1/";
    @GET("escritores")
    Call<List<AuthorModel>> searchAuthors();

}
