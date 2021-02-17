package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixster.Models.Movies;

import org.parceler.Parcels;

public class DetailsActivity extends AppCompatActivity {

    TextView tvTitle;
    RatingBar ratingBar;
    TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTitle = findViewById(R.id.tvTitle);
        ratingBar = findViewById(R.id.ratingBar);
        tvOverview = findViewById(R.id.tvOverview);

        Movies movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());
    }
}