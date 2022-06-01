package com.haneetarya.movieshub.NetworkUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    public static String baseUrl = "https://api.themoviedb.org/";
    public static OkHttpClient getInstance() { return new OkHttpClient().newBuilder().build();}

    public static Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
            GsonConverterFactory.create()
    ).build();

    public static NetworkCalls net = retrofit.create(NetworkCalls.class);


}
