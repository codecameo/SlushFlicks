package com.sifat.slushflicks.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.INVALID_ID

@Entity(tableName = TABLE_NAME_MOVIE)
data class MovieModel(
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
    @ColumnInfo(name = "budget")
    val budget: Long = 0L,
    @ColumnInfo(name = "revenue")
    val revenue: Long = 0L,
    @ColumnInfo(name = "runtime")
    val runtime: Int = 0,
    @ColumnInfo(name = "status")
    val status: String = EMPTY_STRING,
    @ColumnInfo(name = "tagline")
    val tagline: String = EMPTY_STRING,
    @ColumnInfo(name = "video")
    val video: String = EMPTY_STRING,
    @ColumnInfo(name = "casts")
    val casts: List<CastModel> = emptyList()
)

/**
"id": 545609,
"video": false,
"vote_count": 265,
"vote_average": 7.1,
"title": "Extraction",
"release_date": "2020-04-24",
"original_language": "en",
"original_title": "Extraction",
"genre_ids": [
28,
18,
53
],
"backdrop_path": "/1R6cvRtZgsYCkh8UFuWFN33xBP4.jpg",
"adult": false,
"overview": "Tyler Rake, a fearless mercenary who offers his services on the black market, embarks on a dangerous mission when he is hired to rescue the kidnapped son of a Mumbai crime lord…",
"poster_path": "/nygOUcBKPHFTbxsYRFZVePqgPK6.jpg",
"popularity": 124.207,
"media_type": "movie"
 * */
