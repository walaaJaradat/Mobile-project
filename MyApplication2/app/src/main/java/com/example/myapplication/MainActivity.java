package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.ui.favorites.FavoritesActivity;
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
    private Button btnFavorites;
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

        bindViews();
        initViewModel();
        setupSpinners();
        setupClickListeners();
        observeViewModel();
    }

    private void bindViews() {
        spGenre = findViewById(R.id.spGenre);
        spYear = findViewById(R.id.spYear);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnFavorites = findViewById(R.id.btnFavorites);
        bGenre = findViewById(R.id.bGenre);
        bYear = findViewById(R.id.bYear);
        action = findViewById(R.id.action);
        comedy = findViewById(R.id.comedy);
        drama = findViewById(R.id.drama);
        kids = findViewById(R.id.kids);
        recyclerAction = findViewById(R.id.recyclerAction);
        recyclerComedy = findViewById(R.id.recyclerComedy);
        recyclerDrama = findViewById(R.id.recyclerDrama);
        recyclerKids = findViewById(R.id.recyclerKids);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
    }

    private void setupSpinners() {
        spGenre.setAdapter(createDarkAdapter(viewModel.getGenres()));
        spYear.setAdapter(createDarkAdapter(viewModel.getYears()));
    }

    private void observeViewModel() {
        viewModel.getUiState().observe(this, state -> {
            setSectionVisible(action, recyclerAction, state.showAction);
            setSectionVisible(comedy, recyclerComedy, state.showComedy);
            setSectionVisible(drama, recyclerDrama, state.showDrama);
            setSectionVisible(kids, recyclerKids, state.showKids);

            setupRecyclerWithMovies(recyclerAction, state.actionMovies);
            setupRecyclerWithMovies(recyclerComedy, state.comedyMovies);
            setupRecyclerWithMovies(recyclerDrama, state.dramaMovies);
            setupRecyclerWithMovies(recyclerKids, state.kidsMovies);
        });

        viewModel.getSearchQuery().observe(this, query -> {
            if (query != null && !query.equals(edtSearch.getText().toString())) {
                edtSearch.setText(query);
                edtSearch.setSelection(query.length());
            }
        });

        viewModel.getSelectedGenre().observe(this, genre -> {
            if (genre != null) {
                selectSpinnerItem(spGenre, genre);
            }
        });

        viewModel.getSelectedYear().observe(this, year -> {
            if (year != null) {
                selectSpinnerItem(spYear, year);
            }
        });

        viewModel.restoreLastState();
    }

    private void setupClickListeners() {
        btnSearch.setOnClickListener(v ->
                viewModel.searchByTitle(edtSearch.getText().toString().trim()));

        btnFavorites.setOnClickListener(v ->
                startActivity(new Intent(this, FavoritesActivity.class)));

        bGenre.setOnClickListener(v ->
                viewModel.filterByGenre(spGenre.getSelectedItem().toString()));

        bYear.setOnClickListener(v ->
                viewModel.filterByYear(spYear.getSelectedItem().toString()));
    }

    private void setupRecyclerWithMovies(RecyclerView recyclerView, List<Movie> movies) {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(new MovieAdapter(movies));
    }

    private void selectSpinnerItem(Spinner spinner, String value) {
        ArrayAdapter<?> adapter = (ArrayAdapter<?>) spinner.getAdapter();
        if (adapter == null) {
            return;
        }

        for (int i = 0; i < adapter.getCount(); i++) {
            if (value.equalsIgnoreCase(String.valueOf(adapter.getItem(i)))) {
                spinner.setSelection(i);
                break;
            }
        }
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

    private void setSectionVisible(TextView title, RecyclerView recyclerView, boolean visible) {
        int visibility = visible ? View.VISIBLE : View.GONE;
        title.setVisibility(visibility);
        recyclerView.setVisibility(visibility);
    }
}
