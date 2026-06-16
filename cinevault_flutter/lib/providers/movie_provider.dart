import 'package:flutter/foundation.dart';

import '../data/movie_data.dart';
import '../models/movie.dart';

class MovieProvider extends ChangeNotifier {
  final List<Movie> _allMovies = List<Movie>.from(movies);
  final List<Movie> _favorites = [];
  String _query = '';
  String _selectedGenre = 'All Genres';
  String _selectedYear = 'All Years';
  String _selectedRating = 'All Ratings';

  String get selectedGenre => _selectedGenre;

  String get selectedYear => _selectedYear;

  String get selectedRating => _selectedRating;

  List<String> get genreOptions => genres;

  List<String> get yearOptions => years;

  List<String> get ratingOptions => ratings;

  List<Movie> get favorites => List.unmodifiable(_favorites);

  List<Movie> get filteredMovies {
    return _allMovies.where(_matchesCurrentFilters).toList();
  }

  Map<String, List<Movie>> get groupedMovies {
    final grouped = <String, List<Movie>>{};

    for (final genre in genres.where((genre) => genre != 'All Genres')) {
      final sectionMovies = filteredMovies
          .where((movie) => movie.genre == genre)
          .toList();
      if (sectionMovies.isNotEmpty) {
        grouped[genre] = sectionMovies;
      }
    }

    return grouped;
  }

  bool get hasResults => filteredMovies.isNotEmpty;

  void updateSearch(String value) {
    _query = value.trim();
    notifyListeners();
  }

  void updateGenre(String value) {
    _selectedGenre = value;
    notifyListeners();
  }

  void updateYear(String value) {
    _selectedYear = value;
    notifyListeners();
  }

  void updateRating(String value) {
    _selectedRating = value;
    notifyListeners();
  }

  bool isFavorite(Movie movie) {
    return _favorites.any((favorite) => favorite.title == movie.title);
  }

  void toggleFavorite(Movie movie) {
    if (isFavorite(movie)) {
      _favorites.removeWhere((favorite) => favorite.title == movie.title);
    } else {
      _favorites.add(movie);
    }
    notifyListeners();
  }

  bool _matchesCurrentFilters(Movie movie) {
    final matchesQuery = movie.title.toLowerCase().contains(
      _query.toLowerCase(),
    );
    final matchesGenre =
        _selectedGenre == 'All Genres' || movie.genre == _selectedGenre;
    final matchesYear =
        _selectedYear == 'All Years' || movie.year.toString() == _selectedYear;
    final matchesRating =
        _selectedRating == 'All Ratings' ||
        movie.rating >= double.parse(_selectedRating.replaceAll('+', ''));

    return matchesQuery && matchesGenre && matchesYear && matchesRating;
  }
}
