import 'package:flutter/material.dart';
import 'models/movie.dart';
import 'screens/movie_detail_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'MVVM Movie',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(colorScheme: ColorScheme.dark()),
      home: const MovieListScreen(),
    );
  }
}

class MovieListScreen extends StatelessWidget {
  const MovieListScreen({super.key});

  static final List<Movie> movies = [
    Movie(id: 1, title: 'Inception', year: '2010',
        posterUrl: 'https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg',
        overview: 'A thief who steals corporate secrets through dream-sharing technology.', rating: 8.8),
    Movie(id: 2, title: 'The Dark Knight', year: '2008',
        posterUrl: 'https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg',
        overview: 'Batman faces the Joker, a criminal mastermind who wants to create chaos.', rating: 9.0),
    Movie(id: 3, title: 'Interstellar', year: '2014',
        posterUrl: 'https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg',
        overview: 'A team of explorers travel through a wormhole in space.', rating: 8.6),
    Movie(id: 4, title: 'The Matrix', year: '1999',
        posterUrl: 'https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg',
        overview: 'A hacker discovers the world is a simulation.', rating: 8.7),
    Movie(id: 5, title: 'Parasite', year: '2019',
        posterUrl: 'https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg',
        overview: 'A poor family schemes to infiltrate a wealthy household.', rating: 8.5),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF0D1B2A),
      appBar: AppBar(
        backgroundColor: const Color(0xFF0D1B2A),
        title: const Text('🎬 Movies', style: TextStyle(color: Colors.white)),
      ),
      body: ListView.builder(
        itemCount: movies.length,
        itemBuilder: (context, index) {
          final movie = movies[index];
          return GestureDetector(
            onTap: () => Navigator.push(
              context,
              MaterialPageRoute(
                builder: (_) => MovieDetailScreen(movie: movie),
              ),
            ),
            child: Container(
              margin: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
              padding: const EdgeInsets.all(8),
              color: const Color(0xFF1A2A3A),
              child: Row(
                children: [
                  Image.network(movie.posterUrl, width: 60, height: 90, fit: BoxFit.cover),
                  const SizedBox(width: 12),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(movie.title, style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold)),
                        Text(movie.year, style: const TextStyle(color: Colors.grey)),
                        Text('★ ${movie.rating}', style: const TextStyle(color: Colors.red)),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          );
        },
      ),
    );
  }
}