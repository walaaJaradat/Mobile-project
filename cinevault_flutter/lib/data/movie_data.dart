import '../models/movie.dart';

final List<Movie> movies = [

  Movie(
    title: 'John Wick 4',
    genre: 'Action',
    rating: 8.1,
    year: 2023,
    description: 'Action movie',
    imageName: 'johnwick.png',
  ),

  Movie(
    title: 'Mission: Impossible',
    genre: 'Action',
    rating: 7.8,
    year: 2023,
    description: 'Spy action movie',
    imageName: 'mission.png',
  ),

  Movie(
    title: 'The Dark Knight',
    genre: 'Action',
    rating: 9.0,
    year: 2008,
    description: 'Batman crime action movie',
    imageName: 'darkknight.png',
  ),

  Movie(
    title: 'Mad Max: Fury Road',
    genre: 'Action',
    rating: 8.1,
    year: 2015,
    description: 'Post-apocalyptic action movie',
    imageName: 'madmax.png',
  ),

  Movie(
    title: 'The Hangover',
    genre: 'Comedy',
    rating: 7.7,
    year: 2009,
    description: 'Comedy movie',
    imageName: 'hangover.png',
  ),

  Movie(
    title: 'Frozen',
    genre: 'Kids',
    rating: 7.4,
    year: 2013,
    description: 'Animated kids movie',
    imageName: 'frozen.png',
  ),

];

final List<String> genres = [
  'All Genres',
  'Action',
  'Comedy',
  'Drama',
  'Kids',
];

final List<String> years = [
  'All Years',
  '2024',
  '2023',
  '2022',
  '2021',
  '2020',
  '2019',
  '2018',
  '2015',
  '2013',
  '2009',
  '2008',
];