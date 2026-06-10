import 'package:flutter/material.dart';

class SearchBox extends StatelessWidget {
  const SearchBox({super.key, required this.onChanged});

  final ValueChanged<String> onChanged;

  @override
  Widget build(BuildContext context) {
    return TextField(
      onChanged: onChanged,
      style: const TextStyle(color: Colors.white),
      decoration: InputDecoration(
        hintText: 'Search movies...',
        hintStyle: const TextStyle(color: Color(0xFFB0B0B0)),
        prefixIcon: const Icon(Icons.search, color: Color(0xFFB0B0B0)),
        filled: true,
        fillColor: const Color(0xFF141414),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(6),
          borderSide: BorderSide.none,
        ),
      ),
    );
  }
}
