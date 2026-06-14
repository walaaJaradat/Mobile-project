package com.example.myapplication.viewmodel;

import java.util.List;
import java.util.ArrayList;

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

    public List<Movie> getMoviesByGenre(String genre) {
        List<Movie> filtered = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }

    public List<Movie> getMoviesByGenreAndYear(String genre, String year) {
        List<Movie> filtered = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)
                    && String.valueOf(movie.getYear()).equals(year)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }

    public List<Movie> searchMoviesByTitleAndGenre(String title, String genre) {
        List<Movie> filtered = new ArrayList<>();
        String normalizedTitle = title.toLowerCase();

        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)
                    && movie.getTitle().toLowerCase().contains(normalizedTitle)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }
}
