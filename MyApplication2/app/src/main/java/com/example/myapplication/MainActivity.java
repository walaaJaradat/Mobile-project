package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.viewmodel.MovieViewModel;

import java.util.List;

import model.Movie;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerAction;
    private RecyclerView recyclerComedy;
    private RecyclerView recyclerDrama;
    private RecyclerView recyclerKids;
    private Spinner spGenre;
    private Spinner spYear;
    private EditText edtSearch;
    private Button btnSearch;
    private Button bGenre;
    private Button bYear;
    private TextView action;
    private TextView comedy;
    private TextView drama;
    private TextView kids;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new MovieViewModel();

        spGenre = findViewById(R.id.spGenre);
        spYear = findViewById(R.id.spYear);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        bGenre = findViewById(R.id.bGenre);
        bYear = findViewById(R.id.bYear);
        action = findViewById(R.id.action);
        comedy = findViewById(R.id.comedy);
        drama = findViewById(R.id.drama);
        kids = findViewById(R.id.kids);

        spGenre.setAdapter(createDarkAdapter(viewModel.getGenres()));
        spYear.setAdapter(createDarkAdapter(viewModel.getYears()));

        recyclerAction = findViewById(R.id.recyclerAction);
        recyclerComedy = findViewById(R.id.recyclerComedy);
        recyclerDrama = findViewById(R.id.recyclerDrama);
        recyclerKids = findViewById(R.id.recyclerKids);

        showAllMovies();
        setupClickListeners();
    }

    private void setupClickListeners() {
        btnSearch.setOnClickListener(v -> {
            String title = edtSearch.getText().toString().trim();

            if (title.isEmpty()) {
                showAllMovies();
            } else {
                showSearchResults(title);
            }
        });

        bGenre.setOnClickListener(v -> {
            String selectedGenre = spGenre.getSelectedItem().toString();

            if (selectedGenre.equalsIgnoreCase("All Genres")) {
                showAllMovies();
            } else {
                showSelectedGenre(selectedGenre);
            }
        });

        bYear.setOnClickListener(v -> {
            String selectedYear = spYear.getSelectedItem().toString();

            if (selectedYear.equalsIgnoreCase("All Years")) {
                showAllMovies();
            } else {
                showMoviesByYear(selectedYear);
            }
        });
    }

    private ArrayAdapter<String> createDarkAdapter(String[] items) {
        return new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextColor(Color.WHITE);
                view.setTextSize(15);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setTextColor(Color.WHITE);
                view.setBackgroundColor(Color.rgb(20, 20, 20));
                return view;
            }
        };
    }

    private void setupRecycler(RecyclerView recyclerView, String genre) {
        setupRecyclerWithMovies(recyclerView, viewModel.getMoviesByGenre(genre));
    }

    private void setupRecyclerWithMovies(RecyclerView recyclerView, List<Movie> movies) {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(new MovieAdapter(movies));
    }

    private void showAllMovies() {
        setSectionVisible(action, recyclerAction, true);
        setSectionVisible(comedy, recyclerComedy, true);
        setSectionVisible(drama, recyclerDrama, true);
        setSectionVisible(kids, recyclerKids, true);

        setupRecycler(recyclerAction, "Action");
        setupRecycler(recyclerComedy, "Comedy");
        setupRecycler(recyclerDrama, "Drama");
        setupRecycler(recyclerKids, "Kids");
    }

    private void showSelectedGenre(String genre) {
        setSectionVisible(action, recyclerAction, genre.equalsIgnoreCase("Action"));
        setSectionVisible(comedy, recyclerComedy, genre.equalsIgnoreCase("Comedy"));
        setSectionVisible(drama, recyclerDrama, genre.equalsIgnoreCase("Drama"));
        setSectionVisible(kids, recyclerKids, genre.equalsIgnoreCase("Kids"));

        if (genre.equalsIgnoreCase("Action")) {
            setupRecycler(recyclerAction, "Action");
        } else if (genre.equalsIgnoreCase("Comedy")) {
            setupRecycler(recyclerComedy, "Comedy");
        } else if (genre.equalsIgnoreCase("Drama")) {
            setupRecycler(recyclerDrama, "Drama");
        } else if (genre.equalsIgnoreCase("Kids")) {
            setupRecycler(recyclerKids, "Kids");
        }
    }

    private void showMoviesByYear(String year) {
        setSectionVisible(action, recyclerAction, true);
        setSectionVisible(comedy, recyclerComedy, true);
        setSectionVisible(drama, recyclerDrama, true);
        setSectionVisible(kids, recyclerKids, true);

        setupRecyclerWithMovies(recyclerAction, viewModel.getMoviesByGenreAndYear("Action", year));
        setupRecyclerWithMovies(recyclerComedy, viewModel.getMoviesByGenreAndYear("Comedy", year));
        setupRecyclerWithMovies(recyclerDrama, viewModel.getMoviesByGenreAndYear("Drama", year));
        setupRecyclerWithMovies(recyclerKids, viewModel.getMoviesByGenreAndYear("Kids", year));
    }

    private void showSearchResults(String title) {
        setSectionVisible(action, recyclerAction, true);
        setSectionVisible(comedy, recyclerComedy, true);
        setSectionVisible(drama, recyclerDrama, true);
        setSectionVisible(kids, recyclerKids, true);

        setupRecyclerWithMovies(recyclerAction, viewModel.searchMoviesByTitleAndGenre(title, "Action"));
        setupRecyclerWithMovies(recyclerComedy, viewModel.searchMoviesByTitleAndGenre(title, "Comedy"));
        setupRecyclerWithMovies(recyclerDrama, viewModel.searchMoviesByTitleAndGenre(title, "Drama"));
        setupRecyclerWithMovies(recyclerKids, viewModel.searchMoviesByTitleAndGenre(title, "Kids"));
    }

    private void setSectionVisible(TextView title, RecyclerView recyclerView, boolean visible) {
        int visibility = visible ? View.VISIBLE : View.GONE;
        title.setVisibility(visibility);
        recyclerView.setVisibility(visibility);
    }
}
