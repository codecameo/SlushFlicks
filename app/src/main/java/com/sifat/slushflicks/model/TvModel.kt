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
    val budget: Long = 0L,
    val revenue: Long = 0L,
    val runtime: Int = 0,
    val status: String = EMPTY_STRING,
    val tagline: String = EMPTY_STRING,
    val video: String = EMPTY_STRING,
    val casts: List<CastModel> = emptyList()
)