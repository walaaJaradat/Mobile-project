package repository;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import model.Movie;
import model.MovieResponse;
import network.MovieApiService;
import network.RetrofitClient;
import retrofit2.Call;

public class MovieRepository {

    private MovieApiService apiService;

    public MovieRepository() {
        apiService = RetrofitClient
                .getClient()
                .create(MovieApiService.class);
    }

    public Call<MovieResponse> getPopularMovies(String apiKey) {
        return apiService.getPopularMovies(apiKey);
    }

    public List<Movie> getLocalMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("John Wick 4", "Action", 8.1, 2023, "Action movie", R.drawable.johnwick));
        movies.add(new Movie("Mission: Impossible", "Action", 7.8, 2023, "Spy action movie", R.drawable.mission));
        movies.add(new Movie("The Dark Knight", "Action", 9.0, 2008, "Batman crime action movie", R.drawable.darkknight));
        movies.add(new Movie("Mad Max: Fury Road", "Action", 8.1, 2015, "Post-apocalyptic action movie", R.drawable.madmax));

        movies.add(new Movie("The Hangover", "Comedy", 7.7, 2009, "Comedy movie", R.drawable.hangover));
        movies.add(new Movie("Superbad", "Comedy", 7.6, 2007, "Teen comedy movie", R.drawable.superbad));
        movies.add(new Movie("Night at the Museum", "Comedy", 6.5, 2006, "Family comedy movie", R.drawable.museum));
        movies.add(new Movie("Home Alone", "Comedy", 7.6, 1990, "Christmas comedy movie", R.drawable.homealone));

        movies.add(new Movie("The Shawshank Redemption", "Drama", 9.3, 1994, "Prison drama movie", R.drawable.shawshank));
        movies.add(new Movie("Forrest Gump", "Drama", 8.8, 1994, "Life drama movie", R.drawable.forrest));
        movies.add(new Movie("The Godfather", "Drama", 9.2, 1972, "Crime drama movie", R.drawable.godfather));
        movies.add(new Movie("A Beautiful Mind", "Drama", 8.2, 2001, "Biographical drama movie", R.drawable.beautifulmind));

        movies.add(new Movie("Frozen", "Kids", 7.4, 2013, "Animated kids movie", R.drawable.frozen));
        movies.add(new Movie("Toy Story 4", "Kids", 7.7, 2019, "Animated kids movie", R.drawable.toystory));
        movies.add(new Movie("The Lion King", "Kids", 6.9, 2019, "Family adventure movie", R.drawable.lionking));
        movies.add(new Movie("Spider-Man: Into the Spider-Verse", "Kids", 8.4, 2018, "Animated superhero movie", R.drawable.spiderman));

        return movies;
    }

    public String[] getGenres() {
        return new String[]{"All Genres", "Action", "Comedy", "Drama", "Kids"};
    }

    public String[] getYears() {
        return new String[]{
                "All Years",
                "2024",
                "2023",
                "2022",
                "2021",
                "2020",
                "2019",
                "2018",
                "2015",
                "2013",
                "2009",
                "2008",
                "2007",
                "2006",
                "2001",
                "1994",
                "1990",
                "1972"
        };
    }
}