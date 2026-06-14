import 'package:flutter/material.dart';
import '../../viewmodels/quiz_viewmodel.dart';

const darkNavy = Color(0xFF050B14);
const darkGray = Color(0xFF141414);
const red = Color(0xFFE50914);
const subtitleGray = Color(0xFF9AA7B8);

class DetailsScreen extends StatelessWidget {
  final QuizViewModel viewModel;

  const DetailsScreen({super.key, required this.viewModel});

  @override
  Widget build(BuildContext context) {
    final question = viewModel.currentQuestion;

    return Scaffold(
      backgroundColor: darkNavy,
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Top Bar
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  GestureDetector(
                    onTap: () => Navigator.pop(context),
                    child: const Text(
                      "← Back",
                      style: TextStyle(color: subtitleGray, fontSize: 16),
                    ),
                  ),
                  Text(
                    question.category,
                    style: const TextStyle(color: subtitleGray, fontSize: 14),
                  ),
                ],
              ),

              const SizedBox(height: 24),

              // Title
              const Text(
                "Question Details",
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 22,
                  fontWeight: FontWeight.bold,
                ),
              ),

              const SizedBox(height: 20),

              // Question Card
              _buildCard(
                title: "Question",
                content: question.question,
                contentColor: Colors.white,
              ),

              const SizedBox(height: 12),

              // Correct Answer Card
              _buildCard(
                title: "Correct Answer",
                content: "✓ ${question.correctAnswer}",
                contentColor: Colors.green,
              ),

              const SizedBox(height: 12),

              // User Answer Card
              _buildCard(
                title: "Your Answer",
                content:
                    "${viewModel.isCorrect ? '✓' : '✗'} ${viewModel.userAnswer}",
                contentColor: viewModel.isCorrect ? Colors.green : red,
              ),

              const Spacer(),

              // Result Badge
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: darkGray,
                  borderRadius: BorderRadius.circular(12),
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Icon(
                      viewModel.isCorrect ? Icons.check_circle : Icons.cancel,
                      color: viewModel.isCorrect ? Colors.green : red,
                      size: 24,
                    ),
                    const SizedBox(width: 8),
                    Text(
                      viewModel.isCorrect ? "Correct!" : "Wrong Answer",
                      style: TextStyle(
                        color: viewModel.isCorrect ? Colors.green : red,
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildCard({
    required String title,
    required String content,
    required Color contentColor,
  }) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: darkGray,
        borderRadius: BorderRadius.circular(12),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            title,
            style: const TextStyle(color: subtitleGray, fontSize: 12),
          ),
          const SizedBox(height: 8),
          Text(
            content,
            style: TextStyle(color: contentColor, fontSize: 16),
          ),
        ],
      ),
    );
  }
}
