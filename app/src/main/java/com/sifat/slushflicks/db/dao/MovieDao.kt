package com.sifat.slushflicks.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE_TYPE
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ShowModelMinimal
import org.jetbrains.annotations.TestOnly

@Dao
interface MovieDao : BaseDao<MovieModel> {

    @Query(
        """
        SELECT ${TABLE_NAME_MOVIE}.id, title, overview, voteAvg, backdropPath, genres 
        FROM $TABLE_NAME_MOVIE INNER JOIN $TABLE_NAME_MOVIE_TYPE 
        ON ${TABLE_NAME_MOVIE}.id = ${TABLE_NAME_MOVIE_TYPE}.id 
        WHERE ${TABLE_NAME_MOVIE_TYPE}.collection= :collection 
        ORDER BY ${TABLE_NAME_MOVIE_TYPE}.`index`
    """
    )
    fun getPagedMovieSource(collection: String): DataSource.Factory<Int, ShowModelMinimal>

    @Query("SELECT * FROM $TABLE_NAME_MOVIE WHERE id = :movieId")
    fun listenToMovie(movieId: Long): LiveData<MovieModel>

    @Query("SELECT id FROM $TABLE_NAME_MOVIE WHERE id = :movieId")
    fun getMovieId(movieId: Long): Long?

    @Query("UPDATE $TABLE_NAME_MOVIE SET video = :key WHERE id = :movieId")
    fun update(movieId: Long, key: String)

    @Query("UPDATE $TABLE_NAME_MOVIE SET casts = :casts WHERE id = :movieId")
    fun update(movieId: Long, casts: List<CastModel>)

    @Query(
        """
        UPDATE $TABLE_NAME_MOVIE 
        SET voteCount = :voteCount, voteAvg = :voteAvg, releaseData = :releaseData, 
        popularity = :popularity, genres = :genres, budget = :budget, revenue = :revenue, 
        runtime = :runtime, status = :status, tagline = :tagline 
        WHERE id = :id
    """
    )
    suspend fun update(
        id: Long,
        voteCount: Int,
        voteAvg: Double,
        releaseData: String,
        popularity: Double,
        genres: List<GenreModel>,
        budget: Long,
        revenue: Long,
        runtime: Int,
        status: String,
        tagline: String
    )

    @Transaction
    suspend fun updateOrInsert(model: MovieModel) {
        val id = getMovieId(movieId = model.id)
        if (id == null) {
            insertIgnore(model)
        } else {
            update(
                id = model.id,
                voteCount = model.voteCount,
                voteAvg = model.voteAvg,
                revenue = model.revenue,
                releaseData = model.releaseData,
                budget = model.budget,
                popularity = model.popularity,
                genres = model.genres,
                runtime = model.runtime,
                status = model.status,
                tagline = model.tagline
            )
        }
    }

    @TestOnly
    @Query("SELECT * FROM $TABLE_NAME_MOVIE WHERE id = :movieId")
    fun getMovie(movieId: Long): MovieModel
}