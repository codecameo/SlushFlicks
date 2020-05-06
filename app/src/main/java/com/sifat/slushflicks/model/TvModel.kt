package com.sifat.slushflicks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_TV
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.INVALID_ID

@Entity(tableName = TABLE_NAME_TV)
data class TvModel(
    @PrimaryKey(autoGenerate = false)
    val id: Long = INVALID_ID.toLong(),
    val voteCount: Int = 0,
    val voteAvg: Double = 0.0,
    val title: String = EMPTY_STRING,
    val releaseData: String = EMPTY_STRING,
    val backdropPath: String = EMPTY_STRING,
    val overview: String = EMPTY_STRING,
    val posterPath: String = EMPTY_STRING,
    val popularity: Double = 0.0,
    val genres: List<GenreModel> = emptyList(),
    val runtime: Int = 0,
    val status: String = EMPTY_STRING,
    val video: String = EMPTY_STRING,
    val casts: List<CastModel> = emptyList(),
    val nextEpisode: EpisodeModel? = null,
    val lastEpisode: EpisodeModel? = null,
    val seasons: List<SeasonModel> = emptyList(),
    val numOfEpisode: Int = 0,
    val numOfSeason: Int = 0,
    val directors: String = EMPTY_STRING
)

data class EpisodeModel(
    val id: Long,
    val episodeNumber: Int,
    val airDate: String,
    val name: String,
    val seasonNumber: Int,
    val overview: String,
    val stillPath: String,
    val voteAvg: Double
)

/**
"air_date": "2020-05-01",
"episode_number": 4,
"id": 2239042,
"name": "Damage Control",
"overview": "Andy takes matters into his own hands. Laurie reexamines the life she knew.",
"production_code": "",
"season_number": 1,
"show_id": 87784,
"still_path": "/AqV73RG7IWfwoY8CwGoRwn4wncp.jpg",
"vote_average": 0,
"vote_count": 0
 * */

data class SeasonModel(
    val id: Long,
    val airDate: String,
    val episodeCount: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int
)

/**
"air_date": "2020-04-24",
"episode_count": 5,
"id": 120893,
"name": "Season 1",
"overview": "",
"poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
"season_number": 1
 * */