package com.haneetarya.movieshub.RCVAdapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.haneetarya.movieshub.NetworkModels.Movies;
import com.haneetarya.movieshub.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private MovieClick listener;
    private ArrayList<Movies.Dates.Result> movies;
    Context context;

    public MoviesAdapter(Context context,Movies movies, MovieClick listener) {
        this.movies = movies.getResults();
        this.listener = listener;
        this.context=context;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_items,parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movies.Dates.Result movie = movies.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieOverview.setText(movie.getOverview());
        String vote = movie.getVoteAverage() + " (" + movie.getVoteCount() + ")";
        holder.vote.setText(vote);
        String release = "Release: " + movie.getReleaseDate();
        holder.release.setText(release);
        String imgUrl = "https://image.tmdb.org/t/p/w780" + movie.getPosterPath();
        Picasso.get().load(imgUrl).into(holder.poster);

        holder.itemView.setOnClickListener(view -> listener.onClick(movie));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateList(ArrayList<Movies.Dates.Result> results){
        movies = results;
        notifyDataSetChanged();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle,movieOverview,vote,release;
        ImageView poster;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieOverview = itemView.findViewById(R.id.movieOverview);
            vote = itemView.findViewById(R.id.voteText);
            release = itemView.findViewById(R.id.release);
            poster = itemView.findViewById(R.id.poster);
        }
    }
    public interface MovieClick{
        void onClick(Movies.Dates.Result movie);
    }
}


