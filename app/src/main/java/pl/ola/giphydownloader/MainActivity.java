package pl.ola.giphydownloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import pl.ola.giphydownloader.api.GiphyApiService;
import pl.ola.giphydownloader.json.GiphyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GiphyApiService giphyApiService = GiphyApiService.retrofit.create(GiphyApiService.class);
    List<GiphyResponse.GiphyData> fetchedImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchTrendingPhotos();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void fetchTrendingPhotos() {
        giphyApiService.trending(20, GiphyApiService.API_KEY).enqueue(new Callback<GiphyResponse>() {
            @Override
            public void onResponse(Call<GiphyResponse> call, Response<GiphyResponse> response) {
                if (response.body().meta.status == 200) {
                    fetchedImages = response.body().data;
                    Toast.makeText(MainActivity.this, fetchedImages.size()+"" , Toast.LENGTH_SHORT).show();
                } else {
                    //// TODO: 28.04.2017 error message
                }
            }

            @Override
            public void onFailure(Call<GiphyResponse> call, Throwable t) {
                //// TODO: 28.04.2017 error message
            }
        });
    }
}
