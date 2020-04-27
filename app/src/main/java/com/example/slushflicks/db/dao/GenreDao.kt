package com.example.slushflicks.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.slushflicks.model.GenreModel

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGenres(genres: List<GenreModel>)

    @Query("Select * from GENRE_TABLE")
    suspend fun getAllGenres() : List<GenreModel>
}