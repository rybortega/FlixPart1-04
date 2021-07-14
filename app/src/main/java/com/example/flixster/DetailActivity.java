package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.Adapter.MoviesAdapter;
import com.example.flixster.Models.Movies;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    public static final String VIDEO_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=%s";
    public static final String TAG = "DetailActivity";

    YouTubePlayerView youtubePlayer;
    TextView tvName;
    RatingBar ratingBar;
    TextView tvSynopsis;
    TextView tvReleaseDate;
    int viewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        youtubePlayer = findViewById(R.id.youtubePlayer);
        tvName = findViewById(R.id.tvName);
        ratingBar = findViewById(R.id.ratingBar);
        tvSynopsis = findViewById(R.id.tvSynopsis);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        Movies movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        viewType = getIntent().getIntExtra("viewType", 0);


        tvName.setText(movie.getTitle());
        ratingBar.setRating(((float) movie.getRating()));
        tvSynopsis.setText(movie.getOverview());
        tvReleaseDate.setText("Release date: " + movie.getReleaseDate());
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEO_URL, movie.getMovieId(), getString(R.string.TMDb_API_KEY)), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess video API");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    if(results.length() == 0) {
                        return;
                    }
                    String videoKey = results.getJSONObject(0).getString("key");
                    Initialize(videoKey);
                } catch (JSONException e) {
                    Log.e(TAG, "Cannot get results", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.e(TAG, "onFailure video API", throwable);
            }
        });
    }

    private void Initialize(String videoKey) {
            youtubePlayer.initialize(getString(R.string.YOUTUBE_API_KEY), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (viewType == 1) {
                    youTubePlayer.cueVideo(videoKey);
                } else if (viewType == 2) {
                    youTubePlayer.loadVideo(videoKey);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "Failed to cue video");
            }
        });
    }
}