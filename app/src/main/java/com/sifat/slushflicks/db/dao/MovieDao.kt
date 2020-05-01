package com.sifat.slushflicks.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE_TYPE
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.MovieModelMinimal

@Dao
interface MovieDao : BaseDao<MovieModel> {

    @Query("SELECT * FROM $TABLE_NAME_MOVIE INNER JOIN $TABLE_NAME_MOVIE_TYPE ON ${TABLE_NAME_MOVIE}.id = ${TABLE_NAME_MOVIE_TYPE}.id WHERE ${TABLE_NAME_MOVIE_TYPE}.collection= :collection ORDER BY ${TABLE_NAME_MOVIE_TYPE}.`index`")
    suspend fun getMovies(collection: String): List<MovieModel>?

    @Query("SELECT ${TABLE_NAME_MOVIE}.id, title, overview, voteAvg, backdropPath, genres FROM $TABLE_NAME_MOVIE INNER JOIN $TABLE_NAME_MOVIE_TYPE ON ${TABLE_NAME_MOVIE}.id = ${TABLE_NAME_MOVIE_TYPE}.id WHERE ${TABLE_NAME_MOVIE_TYPE}.collection= :collection ORDER BY ${TABLE_NAME_MOVIE_TYPE}.`index`")
    fun getPagedMovieSource(collection: String): DataSource.Factory<Int, MovieModelMinimal>

    @Query("SELECT * FROM $TABLE_NAME_MOVIE WHERE id = :movieId")
    fun getMovie(movieId: Long): LiveData<MovieModel>
}