package com.example.flixster.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailsActivity;
import com.example.flixster.Models.Movies;
import com.example.flixster.R;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movies> movies;

    public MovieAdapter(Context context, List<Movies> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    // Usually involves inflating a layout from XML and returning the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    // Involves populating data into the item through holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get movie at passed in position
        Movies movie = movies.get(position);
        //populate data in components defined in VH
        holder.bind(movie);
    }

    @Override
    // Returns the total count of items in the list
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movies movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;

            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageURL = movie.getBackdropPath();
            }
            else {
                imageURL = movie.getPosterPath();
            }
            Glide.with(context).load(imageURL).into(ivPoster);
            //setting onClickListener for whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //start new activity after user taps anywhere
                    Intent i = new Intent(context, DetailsActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
