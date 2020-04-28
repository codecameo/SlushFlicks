package com.example.slushflicks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.slushflicks.db.DbConstant
import com.example.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE
import com.example.slushflicks.utils.INVALID_ID

@Entity(tableName = TABLE_NAME_MOVIE)
data class MovieModel(
    @PrimaryKey(autoGenerate = false)
    val id: Long = INVALID_ID.toLong(),
    val voteCount: Int,
    val voteAvg: Double,
    val title: String,
    val releaseData: String,
    val backdropPath: String,
    val overview: String,
    val posterPath: String,
    val popularity: Double,
    val genres: List<GenreModel>
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
