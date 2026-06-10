package com.example.myapplication.viewmodel;

import java.util.List;

import model.Movie;
import repository.MovieRepository;

public class MovieViewModel {

    private final MovieRepository repository;
    private final List<Movie> movies;

    public MovieViewModel() {
        repository = new MovieRepository();
        movies = repository.getLocalMovies();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public String[] getGenres() {
        return repository.getGenres();
    }

    public String[] getYears() {
        return repository.getYears();
    }
}
