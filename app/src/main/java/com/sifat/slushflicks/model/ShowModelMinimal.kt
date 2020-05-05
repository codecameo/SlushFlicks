package com.sifat.slushflicks.model

import com.sifat.slushflicks.utils.INVALID_ID

data class ShowModelMinimal(
    val id: Long = INVALID_ID.toLong(),
    val voteAvg: Double,
    val title: String,
    val backdropPath: String,
    val overview: String,
    val genres: List<GenreModel>
)