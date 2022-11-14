package ste.com.tp_restapi.services;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ste.com.tp_restapi.models.BookResponse;

public interface ApiService {

    String BASE_URL = "https://www.googleapis.com/books/v1/";
    @GET("volumes")
    Call<BookResponse> searchBooks(@Query("q") String name);

}
