package com.example.myapplication.ui.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.details.DetailsActivity;

import java.util.List;

import model.Movie;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteVH> {
// FavoritesAdapter have the Favorites movie details (to show in Favorite screen )
    private final List<Movie> movies;

    public FavoritesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public FavoriteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new FavoriteVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteVH holder, int position) {
        Movie movie = movies.get(position);

        holder.imgMovie.setImageResource(movie.getImageResId());
        holder.title.setText(movie.getTitle());
        holder.year.setText(String.valueOf(movie.getYear()));
        holder.itemView.setOnClickListener(v ->
                v.getContext().startActivity(DetailsActivity.newIntent(v.getContext(), movie)));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class FavoriteVH extends RecyclerView.ViewHolder {

        private final ImageView imgMovie;
        private final TextView title;
        private final TextView year;

        FavoriteVH(@NonNull View itemView) {
            super(itemView);

            imgMovie = itemView.findViewById(R.id.imgMovie);
            title = itemView.findViewById(R.id.txtTitle);
            year = itemView.findViewById(R.id.txtYear);
        }
    }
}
