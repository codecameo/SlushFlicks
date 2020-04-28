package com.example.slushflicks.model

import com.example.slushflicks.utils.INVALID_ID

data class MovieModelMinimal(
    val id: Long = INVALID_ID.toLong(),
    val voteAvg: Double,
    val title: String,
    val backdropPath: String,
    val overview: String,
    val genres: List<GenreModel>
)