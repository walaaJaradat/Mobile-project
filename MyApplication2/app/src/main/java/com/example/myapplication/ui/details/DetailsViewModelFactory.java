package com.example.myapplication.ui.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import repository.MovieRepository;

public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository repository;

    public DetailsViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
