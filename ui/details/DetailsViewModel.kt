package com.example.mvvm.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.local.FavoriteEntity
import com.example.mvvm.data.repository.QuizRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: QuizRepository) : ViewModel() {

    fun addToFavorites(favorite: FavoriteEntity) {
        viewModelScope.launch {
            repository.addToFavorites(favorite)
        }
    }

    fun removeFromFavorites(favorite: FavoriteEntity) {
        viewModelScope.launch {
            repository.removeFromFavorites(favorite)
        }
    }
}