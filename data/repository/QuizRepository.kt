package com.example.mvvm.data.repository

import androidx.lifecycle.LiveData
import com.example.mvvm.data.local.AppDatabase
import com.example.mvvm.data.local.FavoriteEntity

class QuizRepository(private val database: AppDatabase) {

    val allFavorites: LiveData<List<FavoriteEntity>> =
        database.favoriteDao().getAllFavorites()

    suspend fun addToFavorites(favorite: FavoriteEntity) {
        database.favoriteDao().addFavorite(favorite)
    }

    suspend fun removeFromFavorites(favorite: FavoriteEntity) {
        database.favoriteDao().removeFavorite(favorite)
    }
}