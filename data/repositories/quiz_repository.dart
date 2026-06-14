import '../models/question.dart';

class QuizRepository {
  final List<Question> _questions = [
    Question(
      id: 1,
      question: "What is the capital of Australia?",
      options: ["Sydney", "Melbourne", "Canberra", "Brisbane"],
      correctAnswer: "Canberra",
      category: "Geography",
    ),
    Question(
      id: 2,
      question: "What is the speed of light?",
      options: ["300,000 km/s", "150,000 km/s", "450,000 km/s", "200,000 km/s"],
      correctAnswer: "300,000 km/s",
      category: "Science",
    ),
    Question(
      id: 3,
      question: "Who painted the Mona Lisa?",
      options: ["Van Gogh", "Picasso", "Da Vinci", "Rembrandt"],
      correctAnswer: "Da Vinci",
      category: "Art",
    ),
  ];

  List<Question> getAllQuestions() => _questions;

  Question getQuestionById(int id) =>
      _questions.firstWhere((q) => q.id == id);
}