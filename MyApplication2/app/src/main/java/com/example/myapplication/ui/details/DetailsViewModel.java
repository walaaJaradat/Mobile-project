package com.example.myapplication.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import repository.MovieRepository;

public class DetailsViewModel extends ViewModel {

    private final MovieRepository repository;
    private final MutableLiveData<Boolean> favoriteState = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();
    private String title;

    public DetailsViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getFavoriteState() {
        return favoriteState;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void setMovieTitle(String title) {
        this.title = title;
        favoriteState.setValue(repository.isFavorite(title));
    }
    // add the favorite movie to the favorite screen
    public void toggleFavorite() {
        boolean newFavoriteState = repository.toggleFavorite(title);
        favoriteState.setValue(newFavoriteState);
        message.setValue(newFavoriteState ? "Added to Favorites" : "Removed from Favorites");
    }
}
