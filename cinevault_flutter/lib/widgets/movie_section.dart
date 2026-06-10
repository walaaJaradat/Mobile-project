import 'package:flutter/material.dart';

import '../models/movie.dart';
import 'movie_card.dart';

class MovieSection extends StatelessWidget {
  const MovieSection({super.key, required this.title, required this.movies});

  final String title;
  final List<Movie> movies;

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
              itemBuilder: (context, index) => MovieCard(movie: movies[index]),
            ),
            const SizedBox(height: 18),
          ],
        );
      },
    );
  }
}
