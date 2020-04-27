package com.example.slushflicks.data

import androidx.lifecycle.LiveData
import com.example.slushflicks.model.GenreModel

interface DatabaseManager {

    suspend fun saveGenre(genres: List<GenreModel>)

    suspend fun loadGenres(): List<GenreModel>
}