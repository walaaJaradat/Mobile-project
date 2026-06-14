package com.example.mvvm.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.local.FavoriteEntity
import com.example.mvvm.data.repository.QuizRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: QuizRepository) : ViewModel() {

    val favorites = repository.allFavorites

    fun removeFromFavorites(favorite: FavoriteEntity) {
        viewModelScope.launch {
            repository.removeFromFavorites(favorite)
        }
    }
}