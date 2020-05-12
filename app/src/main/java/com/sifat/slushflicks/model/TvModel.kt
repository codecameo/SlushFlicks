package com.sifat.slushflicks.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_TV
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.INVALID_ID

@Entity(tableName = TABLE_NAME_TV)
data class TvModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long = INVALID_ID.toLong(),
    @ColumnInfo(name = "voteCount")
    val voteCount: Int = 0,
    @ColumnInfo(name = "voteAvg")
    val voteAvg: Double = 0.0,
    @ColumnInfo(name = "title")
    val title: String = EMPTY_STRING,
    @ColumnInfo(name = "releaseData")
    val releaseData: String = EMPTY_STRING,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String = EMPTY_STRING,
    @ColumnInfo(name = "overview")
    val overview: String = EMPTY_STRING,
    @ColumnInfo(name = "posterPath")
    val posterPath: String = EMPTY_STRING,
    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,
    @ColumnInfo(name = "genres")
    val genres: List<GenreModel> = emptyList(),
    @ColumnInfo(name = "runtime")
    val runtime: Int = 0,
    @ColumnInfo(name = "status")
    val status: String = EMPTY_STRING,
    @ColumnInfo(name = "video")
    val video: String = EMPTY_STRING,
    @ColumnInfo(name = "casts")
    val casts: List<CastModel> = emptyList(),
    @ColumnInfo(name = "nextEpisode")
    val nextEpisode: EpisodeModel? = null,
    @ColumnInfo(name = "lastEpisode")
    val lastEpisode: EpisodeModel? = null,
    @ColumnInfo(name = "seasons")
    val seasons: List<SeasonModel> = emptyList(),
    @ColumnInfo(name = "numOfEpisode")
    val numOfEpisode: Int = 0,
    @ColumnInfo(name = "numOfSeason")
    val numOfSeason: Int = 0,
    @ColumnInfo(name = "directors")
    val directors: String = EMPTY_STRING
)

data class EpisodeModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("episodeNumber")
    val episodeNumber: Int,
    @SerializedName("airDate")
    val airDate: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("seasonNumber")
    val seasonNumber: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("stillPath")
    val stillPath: String,
    @SerializedName("voteAvg")
    val voteAvg: Double
)

data class SeasonModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("airDate")
    val airDate: String,
    @SerializedName("episodeCount")
    val episodeCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("posterPath")
    val posterPath: String,
    @SerializedName("seasonNumber")
    val seasonNumber: Int
)

/*
        id: Long,
        voteCount: Int,
        voteAvg: Double,
        releaseData: String,
        popularity: Double,
        genres: List<GenreModel>,
        runtime: Int,
        status: String
        nextEpisode: EpisodeModel,
        lastEpisode: EpisodeModel,
        seasons: List<SeasonModel>,
        numOfEpisode: Int,
        numOfSeason: Int,
        directors: String
* */

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

/**
"air_date": "2020-04-24",
"episode_count": 5,
"id": 120893,
"name": "Season 1",
"overview": "",
"poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
"season_number": 1
 * */