package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.data.local.AppDatabase
import com.example.mvvm.data.local.FavoriteEntity
import com.example.mvvm.data.model.Question
import com.example.mvvm.data.repository.QuizRepository
import com.example.mvvm.ui.details.DetailsScreen
import com.example.mvvm.ui.details.DetailsViewModel
import com.example.mvvm.ui.favorites.FavoritesScreen
import com.example.mvvm.ui.favorites.FavoritesViewModel
import com.example.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = QuizRepository(database)

        val detailsViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return DetailsViewModel(repository) as T
                }
            }
        )[DetailsViewModel::class.java]

        val favoritesViewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return FavoritesViewModel(repository) as T
                }
            }
        )[FavoritesViewModel::class.java]

        // Sample question for demo
        val sampleQuestion = Question(
            id = 1,
            question = "What is the capital of Australia?",
            options = listOf("Sydney", "Melbourne", "Canberra", "Brisbane"),
            correctAnswer = "Canberra",
            category = "Geography"
        )

        setContent {
            MVVMTheme {
                var currentScreen by remember { mutableStateOf("details") }

                when (currentScreen) {
                    "details" -> DetailsScreen(
                        question = sampleQuestion.question,
                        correctAnswer = sampleQuestion.correctAnswer,
                        userAnswer = "Sydney",
                        isCorrect = false,
                        category = sampleQuestion.category,
                        onAddToFavorites = { favorite ->
                            detailsViewModel.addToFavorites(favorite)
                        },
                        onNavigateBack = { currentScreen = "favorites" }
                    )
                    "favorites" -> FavoritesScreen(
                        viewModel = favoritesViewModel,
                        onNavigateBack = { currentScreen = "details" }
                    )
                }
            }
        }
    }
}