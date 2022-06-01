package com.haneetarya.movieshub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.haneetarya.movieshub.NetworkModels.Movies;
import com.haneetarya.movieshub.NetworkUtils.RetrofitSingleton;
import com.haneetarya.movieshub.RCVAdapters.MoviesAdapter;
import com.haneetarya.movieshub.databinding.ActivityHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements MoviesAdapter.MovieClick {
    MoviesAdapter adapter;
    private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        binding.movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MoviesAdapter(getApplicationContext(),new Movies(),this);
        binding.movieRecyclerView.setAdapter(adapter);
        Call<Movies> call = RetrofitSingleton.net.getMovies();

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "There's seems an error while fetching movies for you"
                            , Toast.LENGTH_LONG).show();
                    binding.progressLayout.setVisibility(View.GONE);
                    return;
                }

                adapter.updateList(response.body().getResults());
                binding.progressLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(Home.this, "Loading Failed!!", Toast.LENGTH_LONG).show();
                Log.i("Retrofit failed", t.getMessage());
                binding.progressLayout.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void onClick(Movies.Dates.Result movie) {
        Intent intent = new Intent(getApplicationContext(), MovieInfo.class);
        intent.putExtra("movieId", movie.getId());
        startActivity(intent);
    }
}