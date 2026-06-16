package com.example.myapplication.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

import model.Movie;
import repository.MovieRepository;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "title";
    private static final String EXTRA_GENRE = "genre";
    private static final String EXTRA_RATING = "rating";
    private static final String EXTRA_YEAR = "year";
    private static final String EXTRA_DESC = "description";
    private static final String EXTRA_IMAGE = "image";

    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvGenre;
    private TextView tvRating;
    private TextView tvYear;
    private TextView tvDesc;
    private ImageButton btnFavorite;
    private ImageButton btnBack;
    private DetailsViewModel viewModel;

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, movie.getTitle());
        intent.putExtra(EXTRA_GENRE, movie.getGenre());
        intent.putExtra(EXTRA_RATING, movie.getVote_average());
        intent.putExtra(EXTRA_YEAR, movie.getYear());
        intent.putExtra(EXTRA_DESC, movie.getOverview());
        intent.putExtra(EXTRA_IMAGE, movie.getImageResId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        bindViews();
        initViewModel();
        renderMovieDetails();
        observeViewModel();
        setupClickListeners();
        viewModel.setMovieTitle(getMovieTitle());
    }

    private void bindViews() {
        ivPoster = findViewById(R.id.ivPoster);
        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvRating = findViewById(R.id.tvRating);
        tvYear = findViewById(R.id.tvYear);
        tvDesc = findViewById(R.id.tvDesc);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnBack = findViewById(R.id.btnBack);
    }

    private void initViewModel() {
        MovieRepository repository = new MovieRepository(getApplicationContext());
        DetailsViewModelFactory factory = new DetailsViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(DetailsViewModel.class);
    }

    private void observeViewModel() {
        viewModel.getFavoriteState().observe(this, this::updateFavoriteIcon);
        viewModel.getMessage().observe(this, message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
    }

    private void setupClickListeners() {
        btnFavorite.setOnClickListener(v -> viewModel.toggleFavorite());
        btnBack.setOnClickListener(v -> finish());
    }

    private void renderMovieDetails() {
        int image = getIntent().getIntExtra(EXTRA_IMAGE, 0);
        if (image != 0) {
            ivPoster.setImageResource(image);
        }

        tvTitle.setText(getMovieTitle());
        tvGenre.setText(getIntent().getStringExtra(EXTRA_GENRE));
        tvRating.setText("Rating: " + getIntent().getDoubleExtra(EXTRA_RATING, 0.0));
        tvYear.setText(String.valueOf(getIntent().getIntExtra(EXTRA_YEAR, 0)));
        tvDesc.setText(getIntent().getStringExtra(EXTRA_DESC));
    }

    private String getMovieTitle() {
        return getIntent().getStringExtra(EXTRA_TITLE);
    }

    private void updateFavoriteIcon(boolean isFavorite) {
        btnFavorite.setImageResource(isFavorite
                ? android.R.drawable.btn_star_big_on
                : android.R.drawable.btn_star_big_off);
    }
}
