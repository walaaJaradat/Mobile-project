package com.example.myapplication.ui.favorites;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

import model.Movie;
import repository.MovieRepository;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView rvFavorites;
    private TextView tvEmpty;
    private FavoritesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        bindViews();
        initViewModel();
        setupRecyclerView();
        observeViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadFavorites();
    }

    private void bindViews() {
        rvFavorites = findViewById(R.id.rvFavorites);
        tvEmpty = findViewById(R.id.tvEmpty);
    }

    private void initViewModel() {
        MovieRepository repository = new MovieRepository(getApplicationContext());
        FavoritesViewModelFactory factory = new FavoritesViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(FavoritesViewModel.class);
    }

    private void setupRecyclerView() {
        rvFavorites.setLayoutManager(new GridLayoutManager(this, 4));
    }

    private void observeViewModel() {
        viewModel.getFavoriteMovies().observe(this, this::renderFavorites);
    }

    private void renderFavorites(List<Movie> favoriteMovies) {
        boolean isEmpty = favoriteMovies.isEmpty();

        tvEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        rvFavorites.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        rvFavorites.setAdapter(new FavoritesAdapter(favoriteMovies));
    }
}
