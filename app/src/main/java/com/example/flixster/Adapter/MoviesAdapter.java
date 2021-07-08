package com.example.flixster.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.Models.Movies;
import com.example.flixster.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Movies> movies;
    Context context;
    public static final int TYPE_LOW = 1;
    public static final int TYPE_POPULAR = 2;

    public MoviesAdapter(List<Movies> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_LOW) {
            View viewLow = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
            return new ViewHolder(viewLow);
        } else if(viewType == TYPE_POPULAR) {
            View viewPopular = LayoutInflater.from(context).inflate(R.layout.popular_item_movie, parent, false);
            return new PopularViewHolder(viewPopular);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movies movie = movies.get(position);
        if(holder instanceof ViewHolder) {
            ((ViewHolder) holder).bind(movie);
        }else if(holder instanceof PopularViewHolder) {
            ((PopularViewHolder) holder).bind(movie);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        Double rating = movies.get(position).getRating();
        //if rating of movie at this position is greater than 8 then it is popular
        if(rating > 8) {
            return TYPE_POPULAR;
        }
        return TYPE_LOW;
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPopular;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPopular = itemView.findViewById(R.id.ivPopular);
        }

        public void bind(Movies movie) {
            int radius = 30; // corner radius, higher value = more rounded
            Glide.with(context).load(movie.getBackdropPath()).transform(new RoundedCorners(radius)).into(ivPopular);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }

        public void bind(Movies movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String imageUrl;

            //setting url based on orientation
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }
            int radius = 30; // corner radius, higher value = more rounded
            Glide.with(context).load(imageUrl).transform(new RoundedCorners(radius)).into(ivImage);
        }
    }
}
