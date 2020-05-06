package com.sifat.slushflicks.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_TV
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_TV_TYPE
import com.sifat.slushflicks.model.*

@Dao
interface TvDao : BaseDao<TvModel> {
    @Query("SELECT * FROM $TABLE_NAME_TV INNER JOIN $TABLE_NAME_TV_TYPE ON $TABLE_NAME_TV.id = $TABLE_NAME_TV_TYPE.id WHERE $TABLE_NAME_TV_TYPE.collection= :collection ORDER BY $TABLE_NAME_TV_TYPE.`index`")
    suspend fun getTvShows(collection: String): List<TvModel>?

    @Query("SELECT $TABLE_NAME_TV.id, title, overview, voteAvg, backdropPath, genres FROM $TABLE_NAME_TV INNER JOIN $TABLE_NAME_TV_TYPE ON $TABLE_NAME_TV.id = $TABLE_NAME_TV_TYPE.id WHERE $TABLE_NAME_TV_TYPE.collection= :collection ORDER BY $TABLE_NAME_TV_TYPE.`index`")
    fun getPagedTvShowSource(collection: String): DataSource.Factory<Int, ShowModelMinimal>

    @Query("SELECT * FROM $TABLE_NAME_TV WHERE id = :tvShowId")
    fun listenToTvShow(tvShowId: Long): LiveData<TvModel>

    @Query("SELECT id FROM $TABLE_NAME_TV WHERE id = :tvShowId")
    fun getTvShowId(tvShowId: Long): Long?

    @Query("UPDATE $TABLE_NAME_TV SET casts = :casts WHERE id = :tvShowId")
    fun update(tvShowId: Long, casts: List<CastModel>)

    @Query("UPDATE $TABLE_NAME_TV SET video = :key WHERE id = :tvShowId")
    fun update(tvShowId: Long, key: String)

    @Query("UPDATE $TABLE_NAME_TV SET voteCount = :voteCount, voteAvg = :voteAvg, releaseData = :releaseData, popularity = :popularity, genres = :genres, runtime = :runtime, status = :status, nextEpisode = :nextEpisode, lastEpisode = :lastEpisode, seasons = :seasons, numOfEpisode = :numOfEpisode, numOfSeason = :numOfSeason, directors = :directors WHERE id = :id")
    suspend fun update(
        id: Long,
        voteCount: Int,
        voteAvg: Double,
        releaseData: String,
        popularity: Double,
        genres: List<GenreModel>,
        runtime: Int,
        status: String,
        nextEpisode: EpisodeModel?,
        lastEpisode: EpisodeModel?,
        seasons: List<SeasonModel>?,
        numOfEpisode: Int,
        numOfSeason: Int,
        directors: String
    )

    @Transaction
    suspend fun updateOrInsert(model: TvModel) {
        val id = getTvShowId(tvShowId = model.id)
        if (id == null) {
            insertIgnore(model)
        } else {
            update(
                id = model.id,
                voteCount = model.voteCount,
                voteAvg = model.voteAvg,
                releaseData = model.releaseData,
                popularity = model.popularity,
                genres = model.genres,
                runtime = model.runtime,
                status = model.status,
                nextEpisode = model.nextEpisode,
                lastEpisode = model.lastEpisode,
                seasons = model.seasons,
                numOfEpisode = model.numOfEpisode,
                numOfSeason = model.numOfSeason,
                directors = model.directors
            )
        }

    }
}