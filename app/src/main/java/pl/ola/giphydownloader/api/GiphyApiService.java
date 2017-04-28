package pl.ola.giphydownloader.api;

import pl.ola.giphydownloader.json.GiphyResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aleksandra Kusiak on 28.04.2017.
 */

public interface GiphyApiService {
public static final String API_KEY = "dc6zaTOxFJmzC"; // TODO: Replace with production key

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.giphy.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/v1/gifs/trending")
    Call<GiphyResponse> trending(@Query("limit") int limit, @Query("api_key") String apiKey);
}
