package com.example.slushflicks.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE
import com.example.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE_TYPE
import com.example.slushflicks.model.MovieModel

@Dao
interface MovieDao : BaseDao<MovieModel> {

    @Query("SELECT * FROM $TABLE_NAME_MOVIE INNER JOIN $TABLE_NAME_MOVIE_TYPE ON ${TABLE_NAME_MOVIE}.id = ${TABLE_NAME_MOVIE_TYPE}.id WHERE ${TABLE_NAME_MOVIE_TYPE}.collection= :collection ORDER BY ${TABLE_NAME_MOVIE_TYPE}.`index`")
    suspend fun getMovies(collection: String): List<MovieModel>?
}