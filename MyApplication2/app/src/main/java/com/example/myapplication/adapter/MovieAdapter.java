package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

import model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieVH> {

    private final List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVH holder, int position) {
        Movie movie = movies.get(position);

        holder.imgMovie.setImageResource(movie.getImageResId());
        holder.title.setText(movie.getTitle());
        holder.year.setText(String.valueOf(movie.getYear()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieVH extends RecyclerView.ViewHolder {

        private final ImageView imgMovie;
        private final TextView title;
        private final TextView year;

        public MovieVH(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgMovie);
            title = itemView.findViewById(R.id.txtTitle);
            year = itemView.findViewById(R.id.txtYear);
        }
    }
}
