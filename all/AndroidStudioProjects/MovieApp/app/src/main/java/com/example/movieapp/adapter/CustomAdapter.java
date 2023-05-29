package com.example.movieapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.domain.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final List<Movie> movieList;
    Context context;

    Handler mainHandler = new Handler();
    CustomAdapter.ViewHolder holder;

    public CustomAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.holder = holder;
        Movie currentMovie = movieList.get(position);

        new FetchImage(currentMovie.getThumbnailUrl()).start();
//        Picasso.with(context)
//                .load(movieList.get(position).getUrl())
//                .placeholder(R.drawable.ic_launcher_background)
//                .fit()
//                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView photo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.photo = itemView.findViewById(R.id.photo_id);

        }
    }

    public class FetchImage extends Thread {
        String url;
        Bitmap bitmap;

        public FetchImage(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            super.run();

            InputStream inputStream;

            try {
                inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    holder.photo.setImageBitmap(bitmap);
                }
            });

        }
    }
}
