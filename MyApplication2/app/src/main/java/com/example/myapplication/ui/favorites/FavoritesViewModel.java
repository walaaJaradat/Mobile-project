package com.example.myapplication.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import model.Movie;
import repository.MovieRepository;

public class FavoritesViewModel extends ViewModel {

    private final MovieRepository repository;
    private final MutableLiveData<List<Movie>> favoriteMovies = new MutableLiveData<>();

    public FavoritesViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void loadFavorites() {
        favoriteMovies.setValue(repository.getFavoriteMovies());
    }
}
