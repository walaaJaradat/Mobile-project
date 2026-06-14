package com.example.mvvm.data.local

import androidx.lifecycle.LiveData

interface FavoriteDao {
    fun getAllFavorites(): LiveData<List<FavoriteEntity>>
    suspend fun addFavorite(favorite: FavoriteEntity)
    suspend fun removeFavorite(favorite: FavoriteEntity)
    suspend fun getFavoriteById(id: Int): FavoriteEntity?
}