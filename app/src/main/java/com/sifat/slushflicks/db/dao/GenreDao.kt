package com.sifat.slushflicks.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.sifat.slushflicks.model.GenreModel

@Dao
interface GenreDao : BaseDao<GenreModel> {

    @Query("Select * from GENRE_TABLE")
    suspend fun getAllGenres(): List<GenreModel>
}