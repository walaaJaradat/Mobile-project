package com.example.mvvm.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppDatabase private constructor() {

    private val favorites = mutableListOf<FavoriteEntity>()
    private val favoritesLiveData = MutableLiveData<List<FavoriteEntity>>(emptyList())

    fun favoriteDao(): FavoriteDao = object : FavoriteDao {
        override fun getAllFavorites(): LiveData<List<FavoriteEntity>> = favoritesLiveData

        override suspend fun addFavorite(favorite: FavoriteEntity) {
            val newFavorite = favorite.copy(id = favorites.size + 1)
            favorites.add(newFavorite)
            favoritesLiveData.postValue(favorites.toList())
        }

        override suspend fun removeFavorite(favorite: FavoriteEntity) {
            favorites.removeAll { it.id == favorite.id }
            favoritesLiveData.postValue(favorites.toList())
        }

        override suspend fun getFavoriteById(id: Int): FavoriteEntity? {
            return favorites.find { it.id == id }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                AppDatabase().also { INSTANCE = it }
            }
        }
    }
}