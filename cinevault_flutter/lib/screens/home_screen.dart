import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/movie_provider.dart';
import '../widgets/filter_dropdown.dart';
import '../widgets/movie_section.dart';
import '../widgets/search_box.dart';
import 'favorites_screen.dart';
import 'movie_detail_screen.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final provider = context.watch<MovieProvider>();

    return Scaffold(
      backgroundColor: Colors.black,
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Expanded(
                    child: RichText(
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
                  ),
                  IconButton(
                    tooltip: 'Favorites',
                    color: const Color(0xFFE50914),
                    icon: const Icon(Icons.star),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => const FavoritesScreen(),
                        ),
                      );
                    },
                  ),
                ],
              ),
              const SizedBox(height: 18),
              SearchBox(onChanged: context.read<MovieProvider>().updateSearch),
              const SizedBox(height: 18),
              FilterDropdown(
                label: 'GENRE',
                value: provider.selectedGenre,
                items: provider.genreOptions,
                onChanged: context.read<MovieProvider>().updateGenre,
              ),
              const SizedBox(height: 14),
              FilterDropdown(
                label: 'YEAR',
                value: provider.selectedYear,
                items: provider.yearOptions,
                onChanged: context.read<MovieProvider>().updateYear,
              ),
              const SizedBox(height: 14),
              FilterDropdown(
                label: 'RATING',
                value: provider.selectedRating,
                items: provider.ratingOptions,
                onChanged: context.read<MovieProvider>().updateRating,
              ),
              const SizedBox(height: 22),
              if (!provider.hasResults)
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
                for (final entry in provider.groupedMovies.entries)
                  MovieSection(
                    title: '${entry.key} Movies',
                    movies: entry.value,
                    onMovieTap: (movie) {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => MovieDetailScreen(movie: movie),
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
