import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/movie_provider.dart';
import 'movie_detail_screen.dart';

class FavoritesScreen extends StatelessWidget {
  const FavoritesScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final provider = context.watch<MovieProvider>();
    final favorites = provider.favorites;

    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        backgroundColor: Colors.black,
        foregroundColor: Colors.white,
        title: const Text('Favorites'),
      ),
      body: favorites.isEmpty
          ? const Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(Icons.star_border, color: Color(0xFF777777), size: 64),
                  SizedBox(height: 16),
                  Text(
                    'No favorites yet',
                    style: TextStyle(color: Color(0xFFB0B0B0), fontSize: 16),
                  ),
                ],
              ),
            )
          : ListView.builder(
              padding: const EdgeInsets.all(12),
              itemCount: favorites.length,
              itemBuilder: (context, index) {
                final movie = favorites[index];
                return Card(
                  color: const Color(0xFF141414),
                  margin: const EdgeInsets.only(bottom: 10),
                  child: ListTile(
                    contentPadding: const EdgeInsets.all(8),
                    leading: ClipRRect(
                      borderRadius: BorderRadius.circular(4),
                      child: Image.asset(
                        'assets/posters/${movie.imageName}',
                        width: 58,
                        height: 86,
                        fit: BoxFit.cover,
                        errorBuilder: (context, error, stackTrace) {
                          return Container(
                            width: 58,
                            height: 86,
                            color: const Color(0xFF242424),
                            child: const Icon(
                              Icons.movie,
                              color: Color(0xFFE50914),
                            ),
                          );
                        },
                      ),
                    ),
                    title: Text(
                      movie.title,
                      style: const TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    subtitle: Text(
                      '${movie.year} - ${movie.rating.toStringAsFixed(1)}',
                      style: const TextStyle(color: Color(0xFFBBBBBB)),
                    ),
                    trailing: IconButton(
                      icon: const Icon(Icons.star, color: Color(0xFFE50914)),
                      onPressed: () =>
                          context.read<MovieProvider>().toggleFavorite(movie),
                    ),
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => MovieDetailScreen(movie: movie),
                        ),
                      );
                    },
                  ),
                );
              },
            ),
    );
  }
}
