import 'package:flutter/foundation.dart';
import '../data/models/question.dart';
import '../data/repositories/quiz_repository.dart';

class QuizViewModel extends ChangeNotifier {
  final QuizRepository _repository = QuizRepository();

  late Question _currentQuestion;
  String _userAnswer = "";
  bool _isCorrect = false;

  Question get currentQuestion => _currentQuestion;
  String get userAnswer => _userAnswer;
  bool get isCorrect => _isCorrect;

  QuizViewModel() {
    _currentQuestion = _repository.getAllQuestions().first;
    _userAnswer = "Sydney";
    _isCorrect = false;
  }

  void checkAnswer(String answer) {
    _userAnswer = answer;
    _isCorrect = answer == _currentQuestion.correctAnswer;
    notifyListeners();
  }
}