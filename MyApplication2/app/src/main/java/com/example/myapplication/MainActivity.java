package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import model.Movie;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerAction;
    private RecyclerView recyclerComedy;
    private RecyclerView recyclerDrama;
    private RecyclerView recyclerKids;
    private Spinner spGenre;
    private Spinner spYear;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new MovieViewModel();

        spGenre = findViewById(R.id.spGenre);
        spYear = findViewById(R.id.spYear);

        spGenre.setAdapter(createDarkAdapter(viewModel.getGenres()));
        spYear.setAdapter(createDarkAdapter(viewModel.getYears()));

        recyclerAction = findViewById(R.id.recyclerAction);
        recyclerComedy = findViewById(R.id.recyclerComedy);
        recyclerDrama = findViewById(R.id.recyclerDrama);
        recyclerKids = findViewById(R.id.recyclerKids);

        setupRecycler(recyclerAction, "Action");
        setupRecycler(recyclerComedy, "Comedy");
        setupRecycler(recyclerDrama, "Drama");
        setupRecycler(recyclerKids, "Kids");
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
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(new MovieAdapter(filterByGenre(genre)));
    }

    private List<Movie> filterByGenre(String genre) {
        List<Movie> filtered = new ArrayList<>();

        for (Movie movie : viewModel.getMovies()) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                filtered.add(movie);
            }
        }

        return filtered;
    }
}
