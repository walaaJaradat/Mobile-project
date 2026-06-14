import 'package:flutter/material.dart';

import '../models/movie.dart';
import 'movie_card.dart';

class MovieSection extends StatelessWidget {
  const MovieSection({
    super.key,
    required this.title,
    required this.movies,
    this.onMovieTap,
  });

  final String title;
  final List<Movie> movies;
  final ValueChanged<Movie>? onMovieTap;

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        final crossAxisCount = constraints.maxWidth >= 700 ? 6 : 4;

        return Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: const TextStyle(
                color: Colors.white,
                fontSize: 21,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 10),
            GridView.builder(
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              itemCount: movies.length,
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: crossAxisCount,
                childAspectRatio: 0.5,
                crossAxisSpacing: 8,
                mainAxisSpacing: 12,
              ),
              itemBuilder: (context, index) {
                final movie = movies[index];
                return MovieCard(
                  movie: movie,
                  onTap: onMovieTap == null ? null : () => onMovieTap!(movie),
                );
              },
            ),
            const SizedBox(height: 18),
          ],
        );
      },
    );
  }
}
