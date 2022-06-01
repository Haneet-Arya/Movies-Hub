package com.haneetarya.movieshub.NetworkUtils;

import com.haneetarya.movieshub.NetworkModels.MovieDetails;
import com.haneetarya.movieshub.NetworkModels.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkCalls {

    @GET("3/movie/now_playing?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=undefined")
    Call<Movies> getMovies();

    @GET("3/movie/{mid}?api_key=55957fcf3ba81b137f8fc01ac5a31fb5")
    Call<MovieDetails> getMovieDetails(@Path("mid") int mid);
}
