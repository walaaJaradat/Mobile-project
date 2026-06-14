import 'package:flutter/material.dart';

import '../data/movie_data.dart';
import '../models/movie.dart';
import '../widgets/filter_dropdown.dart';
import '../widgets/movie_section.dart';
import '../widgets/search_box.dart';
import 'movie_detail_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  String _query = '';
  String _selectedGenre = 'All Genres';
  String _selectedYear = 'All Years';
  String _selectedRating = 'All Ratings';

  List<Movie> get _filteredMovies {
    return movies.where((movie) {
      final matchesQuery = movie.title.toLowerCase().contains(
        _query.toLowerCase(),
      );
      final matchesGenre =
          _selectedGenre == 'All Genres' || movie.genre == _selectedGenre;
      final matchesYear =
          _selectedYear == 'All Years' ||
          movie.year.toString() == _selectedYear;
      final matchesRating =
          _selectedRating == 'All Ratings' ||
          movie.rating >=
              double.parse(_selectedRating.replaceAll('+', '').trim());
      return matchesQuery && matchesGenre && matchesYear && matchesRating;
    }).toList();
  }

  @override
  Widget build(BuildContext context) {
    final groupedMovies = <String, List<Movie>>{
      for (final genre in genres.where((genre) => genre != 'All Genres'))
        genre: _filteredMovies.where((movie) => movie.genre == genre).toList(),
    };

    return Scaffold(
      backgroundColor: Colors.black,
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              RichText(
                text: const TextSpan(
                  children: [
                    TextSpan(
                      text: 'Cine',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 32,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    TextSpan(
                      text: 'Vault',
                      style: TextStyle(
                        color: Color(0xFFE50914),
                        fontSize: 32,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 18),
              SearchBox(onChanged: (value) => setState(() => _query = value)),
              const SizedBox(height: 18),
              FilterDropdown(
                label: 'GENRE',
                value: _selectedGenre,
                items: genres,
                onChanged: (value) => setState(() => _selectedGenre = value),
              ),
              const SizedBox(height: 14),
              FilterDropdown(
                label: 'YEAR',
                value: _selectedYear,
                items: years,
                onChanged: (value) => setState(() => _selectedYear = value),
              ),
              const SizedBox(height: 14),
              FilterDropdown(
                label: 'RATING',
                value: _selectedRating,
                items: ratings,
                onChanged: (value) => setState(() => _selectedRating = value),
              ),
              const SizedBox(height: 22),
              if (_filteredMovies.isEmpty)
                const Padding(
                  padding: EdgeInsets.symmetric(vertical: 48),
                  child: Center(
                    child: Text(
                      'No movies match your filters.',
                      style: TextStyle(color: Color(0xFFB0B0B0)),
                    ),
                  ),
                )
              else
                for (final entry in groupedMovies.entries)
                  if (entry.value.isNotEmpty)
                    MovieSection(
                      title: '${entry.key} Movies',
                      movies: entry.value,
                      onMovieTap: (movie) {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) =>
                                MovieDetailScreen(movie: movie),
                          ),
                        );
                      },
                    ),
            ],
          ),
        ),
      ),
    );
  }
}
