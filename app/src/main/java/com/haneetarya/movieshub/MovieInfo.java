package com.haneetarya.movieshub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.haneetarya.movieshub.NetworkModels.MovieDetails;
import com.haneetarya.movieshub.NetworkUtils.RetrofitSingleton;
import com.haneetarya.movieshub.databinding.ActivityMovieInfoBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieInfo extends AppCompatActivity {
    ActivityMovieInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int mid = intent.getIntExtra("movieId", 0);
        Call<MovieDetails> call = RetrofitSingleton.net.getMovieDetails(mid);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if(!response.isSuccessful()){
                    binding.progressLayout.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "There's seems an error while fetching movies for you"
                            , Toast.LENGTH_LONG).show();
                    return;
                }
                MovieDetails obj = response.body();
                int i = 0;
                String imgUrl = "https://image.tmdb.org/t/p/w780" + obj.getPosterPath();
                String tagline = "tagline: "+obj.getTagline();
                StringBuilder genre = new StringBuilder("Genres: ") ;
                StringBuilder lang = new StringBuilder("Language: ");
                StringBuilder prodComp = new StringBuilder("Production Companies: ");
                StringBuilder prodCount = new StringBuilder("Production Countries: ");

                for(MovieDetails.Genre e: obj.getGenres()){
                    if(i==0){
                        genre.append(e.getName());
                        i=1;
                    }
                    else{
                        genre.append(", ").append(e.getName());
                    }
                }

                i=0;
                for(MovieDetails.SpokenLanguage e: obj.getSpokenLanguages()){
                    if(i==0){
                        lang.append(e.getEnglishName());
                        i=1;
                    }
                    else{
                        lang.append(", ").append(e.getEnglishName());
                    }
                }

                i=0;
                for(MovieDetails.ProductionCompany e: obj.getProductionCompanies()){
                    if(i==0){
                        prodComp.append(e.getName());
                        i=1;
                    }
                    else{
                        prodComp.append(", ").append(e.getName());
                    }
                }
                i=0;
                for(MovieDetails.ProductionCountry e: obj.getProductionCountries()){
                    if(i==0){
                        prodCount.append(e.getName());
                        i=1;
                    }
                    else{
                        prodCount.append(", ").append(e.getName());
                    }
                }

                String imdb = obj.getImdbId();

                Picasso.get().load(imgUrl).into(binding.poster);
                binding.title.setText(obj.getTitle());
                binding.tagline.setText(tagline);
                binding.genre.setText(genre);
                binding.language.setText(lang);
                binding.voteAverage.setText(Double.toString(obj.getVoteAverage()));
                String voteCount = "Vote Count: " + obj.getVoteCount();
                binding.voteCount.setText(voteCount);
                binding.overview.setText(obj.getOverview());
                String status = "Status: " + obj.getStatus();
                String releaseDate = "Release Date: " + obj.getReleaseDate();
                String budget = "Budget: $"+obj.getBudget();
                String revenue = "Revenue Collected: $" + obj.getRevenue();
                String runtime = "Runtime: "+obj.getRuntime() + " mins";
                String popularity = "Popularity: " + obj.getPopularity();
                binding.releaseStatus.setText(status);
                binding.budget.setText(budget);
                binding.releaseDate.setText(releaseDate);
                binding.revenue.setText(revenue);
                binding.runtime.setText(runtime);
                binding.popularity.setText(popularity);
                binding.productionComp.setText(prodComp);
                binding.productionCountries.setText(prodCount);
                binding.imdb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = "https://www.imdb.com/title/" + imdb;
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.launchUrl(view.getContext(), Uri.parse(url));
                    }
                });
                binding.progressLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Loading Failed!!", Toast.LENGTH_LONG).show();
                binding.progressLayout.setVisibility(View.GONE);
            }
        });
    }
}