package com.example.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import model.Movie;
import repository.MovieRepository;

public class MovieViewModel extends ViewModel {

    public static class MovieUiState {
        public final boolean showAction;
        public final boolean showComedy;
        public final boolean showDrama;
        public final boolean showKids;
        public final List<Movie> actionMovies;
        public final List<Movie> comedyMovies;
        public final List<Movie> dramaMovies;
        public final List<Movie> kidsMovies;

        public MovieUiState(
                boolean showAction,
                boolean showComedy,
                boolean showDrama,
                boolean showKids,
                List<Movie> actionMovies,
                List<Movie> comedyMovies,
                List<Movie> dramaMovies,
                List<Movie> kidsMovies) {
            this.showAction = showAction;
            this.showComedy = showComedy;
            this.showDrama = showDrama;
            this.showKids = showKids;
            this.actionMovies = actionMovies;
            this.comedyMovies = comedyMovies;
            this.dramaMovies = dramaMovies;
            this.kidsMovies = kidsMovies;
        }
    }

    private enum FilterMode {
        ALL,
        GENRE,
        YEAR,
        SEARCH
    }

    private final MovieRepository repository;
    private final List<Movie> allMovies;
    private final MutableLiveData<MovieUiState> uiState = new MutableLiveData<>();
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>("");
    private final MutableLiveData<String> selectedGenre = new MutableLiveData<>("All Genres");
    private final MutableLiveData<String> selectedYear = new MutableLiveData<>("All Years");
    private FilterMode currentMode = FilterMode.ALL;
    private String activeGenre = "All Genres";
    private String activeYear = "All Years";
    private String activeSearch = "";

    public MovieViewModel() {
        repository = new MovieRepository();
        allMovies = repository.getLocalMovies();
        publishAllMovies();
    }

    public LiveData<MovieUiState> getUiState() {
        return uiState;
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public LiveData<String> getSelectedGenre() {
        return selectedGenre;
    }

    public LiveData<String> getSelectedYear() {
        return selectedYear;
    }

    public String[] getGenres() {
        return repository.getGenres();
    }

    public String[] getYears() {
        return repository.getYears();
    }

    public void showAllMovies() {
        currentMode = FilterMode.ALL;
        activeGenre = "All Genres";
        activeYear = "All Years";
        activeSearch = "";
        searchQuery.setValue("");
        selectedGenre.setValue("All Genres");
        selectedYear.setValue("All Years");
        publishAllMovies();
    }

    public void filterByGenre(String genre) {
        selectedGenre.setValue(genre);
        activeGenre = genre;

        if (genre.equalsIgnoreCase("All Genres")) {
            showAllMovies();
            return;
        }

        currentMode = FilterMode.GENRE;
        uiState.setValue(new MovieUiState(
                genre.equalsIgnoreCase("Action"),
                genre.equalsIgnoreCase("Comedy"),
                genre.equalsIgnoreCase("Drama"),
                genre.equalsIgnoreCase("Kids"),
                filterBySelectedGenre("Action", genre),
                filterBySelectedGenre("Comedy", genre),
                filterBySelectedGenre("Drama", genre),
                filterBySelectedGenre("Kids", genre)
        ));
    }

    public void filterByYear(String year) {
        selectedYear.setValue(year);
        activeYear = year;

        if (year.equalsIgnoreCase("All Years")) {
            showAllMovies();
            return;
        }

        currentMode = FilterMode.YEAR;
        uiState.setValue(new MovieUiState(
                true,
                true,
                true,
                true,
                getMoviesByGenreAndYear("Action", year),
                getMoviesByGenreAndYear("Comedy", year),
                getMoviesByGenreAndYear("Drama", year),
                getMoviesByGenreAndYear("Kids", year)
        ));
    }

    public void searchByTitle(String title) {
        String normalized = title == null ? "" : title.trim();
        activeSearch = normalized;
        searchQuery.setValue(normalized);

        if (normalized.isEmpty()) {
            showAllMovies();
            return;
        }

        currentMode = FilterMode.SEARCH;
        uiState.setValue(new MovieUiState(
                true,
                true,
                true,
                true,
                searchMoviesByTitleAndGenre(normalized, "Action"),
                searchMoviesByTitleAndGenre(normalized, "Comedy"),
                searchMoviesByTitleAndGenre(normalized, "Drama"),
                searchMoviesByTitleAndGenre(normalized, "Kids")
        ));
    }

    public void restoreLastState() {
        switch (currentMode) {
            case SEARCH:
                searchByTitle(activeSearch);
                break;
            case GENRE:
                filterByGenre(activeGenre);
                break;
            case YEAR:
                filterByYear(activeYear);
                break;
            case ALL:
            default:
                publishAllMovies();
                break;
        }
    }

    private List<Movie> getMoviesByGenre(String genre) {
        List<Movie> filtered = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }

    private List<Movie> getMoviesByGenreAndYear(String genre, String year) {
        List<Movie> filtered = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (movie.getGenre().equalsIgnoreCase(genre)
                    && String.valueOf(movie.getYear()).equals(year)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }

    private List<Movie> searchMoviesByTitleAndGenre(String title, String genre) {
        List<Movie> filtered = new ArrayList<>();
        String normalizedTitle = title.toLowerCase();

        for (Movie movie : allMovies) {
            if (movie.getGenre().equalsIgnoreCase(genre)
                    && movie.getTitle().toLowerCase().contains(normalizedTitle)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }

    private void publishAllMovies() {
        uiState.setValue(new MovieUiState(
                true,
                true,
                true,
                true,
                getMoviesByGenre("Action"),
                getMoviesByGenre("Comedy"),
                getMoviesByGenre("Drama"),
                getMoviesByGenre("Kids")
        ));
    }

    private List<Movie> filterBySelectedGenre(String sectionGenre, String selectedGenre) {
        if (sectionGenre.equalsIgnoreCase(selectedGenre)) {
            return getMoviesByGenre(sectionGenre);
        }
        return new ArrayList<>();
    }
}
