package com.codepath.atthetheatres.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codepath.atthetheatres.R;
import com.codepath.atthetheatres.adapters.MoviesArrayAdapter;
import com.codepath.atthetheatres.models.Movie;
import com.codepath.atthetheatres.util.MoviesHTTPClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {
    List<Movie> moviesList ;
    MoviesArrayAdapter adapter;
    RecyclerView rvMovies;
    boolean orientationLandscape = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.title));

        moviesList = new ArrayList<>();
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        adapter = new MoviesArrayAdapter(this, moviesList, orientationLandscape);
        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        getMovies();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        orientationLandscape = (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? true : false);
        Log.d("MoviesActivity", "orientation changed");
        adapter.notifyDataSetChanged();
    }

    private void getMovies() {
        MoviesHTTPClient moviesHTTPClient = MoviesHTTPClient.getInstance();
        RequestParams params = new RequestParams();

        moviesHTTPClient.getMovies(params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray moviesJSONArray = null;

                try {
                    moviesJSONArray = response.getJSONArray("results");
                    Log.d("JSON Resp .. " , response.toString());
                    moviesList.addAll(Movie.fromJSONArray(moviesJSONArray));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    // Handle failure
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                // Handle failure
            }
        });
    }
}
