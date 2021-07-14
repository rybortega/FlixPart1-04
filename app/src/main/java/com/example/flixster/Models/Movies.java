package com.example.flixster.Models;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

@Parcel
public class Movies {

    String title;
    String overview;
    String posterPath;
    String backdropPath;
    String releaseDate;
    double rating;
    int movieId;

    // empty constructor needed by the Parceler library
    public Movies() {
    }

    public Movies(JSONObject movie) throws JSONException {
        title = movie.getString("title");
        overview = movie.getString("overview");
        posterPath = movie.getString("poster_path");
        backdropPath = movie.getString("backdrop_path");
        rating = movie.getDouble("vote_average");
        movieId = movie.getInt("id");
        releaseDate = movie.getString("release_date");
    }

    public static List<Movies> getMoviesList(JSONArray results) throws JSONException {
        List<Movies> allMovies = new ArrayList<>();
        for(int i = 0; i < results.length(); i++) {
            allMovies.add(new Movies(results.getJSONObject(i)));
        }
        return allMovies;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/original/%s", backdropPath);
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
