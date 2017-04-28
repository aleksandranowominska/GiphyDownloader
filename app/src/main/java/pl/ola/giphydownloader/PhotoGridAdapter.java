package pl.ola.giphydownloader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import pl.ola.giphydownloader.json.GiphyResponse;

/**
 * Created by Aleksandra Kusiak on 28.04.2017.
 */

public class PhotoGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<GiphyResponse.GiphyData> photos;

    public PhotoGridAdapter(Context mContext, List<GiphyResponse.GiphyData> photos) {
        this.mContext = mContext;
        this.photos = photos;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getCount() {
        if (photos != null) {
            return photos.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int row) {
        return null;
    }

    @Override
    public long getItemId(int row) {
        return 0;
    }

    @Override
    public View getView(int row, View view, ViewGroup viewGroup) {
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_cell, viewGroup, false);

        GiphyResponse.GiphyData photo = photos.get(row);
        String fullURL = photo.images.original.url;

        ImageView imageView = (ImageView) view.findViewById(R.id.downloadedImage);
        Log.d("ble", fullURL);
        Glide.with(mContext)
                .load(fullURL)
                .asGif()
                //.placeholder(R.drawable.loading_gif)
                .into(imageView);

        return view;
    }
}
