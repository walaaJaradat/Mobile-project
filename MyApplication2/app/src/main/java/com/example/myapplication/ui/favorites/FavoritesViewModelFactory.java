package com.example.myapplication.ui.favorites;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import repository.MovieRepository;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository repository;

    public FavoritesViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavoritesViewModel.class)) {
            return (T) new FavoritesViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
