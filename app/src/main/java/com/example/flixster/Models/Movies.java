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
    String backdropPath;
    String title;
    String posterPath;
    String overview;
    double rating;

    //empty constructor needed by Parcel library
    public Movies(){}

    public Movies(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        posterPath = jsonObject.getString("poster_path");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
    }

    public static List<Movies> MovieList(JSONArray jsonArray) throws JSONException {
        List<Movies> movies = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            movies.add(new Movies(jsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath) ;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/original/%s", backdropPath);
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }
}