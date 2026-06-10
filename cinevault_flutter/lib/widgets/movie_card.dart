import 'package:flutter/material.dart';

import '../models/movie.dart';

class MovieCard extends StatelessWidget {
  const MovieCard({super.key, required this.movie});

  final Movie movie;

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Expanded(
          child: ClipRRect(
            borderRadius: BorderRadius.circular(5),
            child: Image.asset(
              'assets/posters/${movie.imageName}',
              width: double.infinity,
              fit: BoxFit.cover,
              errorBuilder: (context, error, stackTrace) {
                return Container(
                  color: const Color(0xFF242424),
                  child: const Center(
                    child: Icon(
                      Icons.movie,
                      color: Color(0xFFE50914),
                      size: 32,
                    ),
                  ),
                );
              },
            ),
          ),
        ),
        const SizedBox(height: 5),
        Text(
          movie.title,
          maxLines: 2,
          overflow: TextOverflow.ellipsis,
          style: const TextStyle(
            color: Colors.white,
            fontSize: 11,
            fontWeight: FontWeight.bold,
          ),
        ),
        Text(
          '${movie.year} - ${movie.rating.toStringAsFixed(1)}',
          style: const TextStyle(color: Color(0xFFBBBBBB), fontSize: 10),
        ),
      ],
    );
  }
}
