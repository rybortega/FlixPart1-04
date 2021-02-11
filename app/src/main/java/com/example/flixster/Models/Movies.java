package com.example.flixster.Models;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class Movies {
    String title;
    String posterPath;
    String overview;

    public Movies(JSONObject jsonObject) throws JSONException {
        title = jsonObject.getString("title");
        posterPath = jsonObject.getString("poster_path");
        overview = jsonObject.getString("overview");
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

    public String getOverview() {
        return overview;
    }

}