package com.codepath.atthetheatres.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by vidhya on 2/12/18.
 */

public class MoviesHTTPClient extends AsyncHttpClient {

    private static final String MOVIES_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed" ;
    //private OkHttpClient httpClient;

    private static MoviesHTTPClient moviesClient;

    public MoviesHTTPClient() {

    }

    public static MoviesHTTPClient getInstance() {
        if(moviesClient == null) {
            moviesClient = new MoviesHTTPClient();
        }
        return moviesClient;
    }

    public void getMovies(RequestParams params, AsyncHttpResponseHandler handler) {
        moviesClient.get(MOVIES_URL, params, handler);
    }
}
