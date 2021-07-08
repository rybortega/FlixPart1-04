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

public class Movies {

    String title;
    String overview;
    String posterPath;
    String backdropPath;
    Double rating;

    public Movies(JSONObject movie) throws JSONException {
        title = movie.getString("title");
        overview = movie.getString("overview");
        posterPath = movie.getString("poster_path");
        backdropPath = movie.getString("backdrop_path");
        rating = movie.getDouble("vote_average");
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

    public Double getRating() {
        return rating;
    }
}
