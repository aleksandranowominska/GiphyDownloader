package pl.ola.giphydownloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.ola.giphydownloader.api.GiphyApiService;
import pl.ola.giphydownloader.json.GiphyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GiphyApiService giphyApiService = GiphyApiService.retrofit.create(GiphyApiService.class);
    List<GiphyResponse.GiphyData> fetchedImages = new ArrayList<>();

    GridView trendingPhotosGridView;
PhotoGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchTrendingPhotos();

        trendingPhotosGridView = (GridView) findViewById(R.id.trendingPhotosGridView);

        adapter = new PhotoGridAdapter(this, fetchedImages);
        trendingPhotosGridView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void fetchTrendingPhotos() {
        giphyApiService.trending(20, GiphyApiService.API_KEY).enqueue(new Callback<GiphyResponse>() {
            @Override
            public void onResponse(Call<GiphyResponse> call, Response<GiphyResponse> response) {
                if (response.raw().code() != 200) {
                    showApiError(response.raw().message());
                    return;
                }
                if (response.body().meta.status != 200) {
                    showApiError(response.body().meta.msg);
                    return;
                }
                fetchedImages.clear();
                fetchedImages.addAll(response.body().data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GiphyResponse> call, Throwable t) {
                showApiError(t.getLocalizedMessage());
            }
        });
    }

    public void showApiError(String errorMessage) {
        Toast.makeText(this, getResources().getString(R.string.error_message, errorMessage), Toast.LENGTH_SHORT).show();
    }
}
