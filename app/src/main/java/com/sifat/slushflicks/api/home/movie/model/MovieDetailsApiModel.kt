package com.sifat.slushflicks.api.home.movie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.model.GenreModel

data class MovieDetailsApiModel(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("video")
    @Expose
    val video: Boolean,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String?,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,
    @SerializedName("original_title")
    @Expose
    val originalTitle: String,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,
    @SerializedName("adult")
    @Expose
    val adult: Boolean,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("genres")
    @Expose
    val genres: List<GenreModel>,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("tagline")
    @Expose
    val tagline: String,
    @SerializedName("revenue")
    @Expose
    val revenue: Long,
    @SerializedName("budget")
    @Expose
    val budget: Long,
    @SerializedName("runtime")
    @Expose
    val runtime: Int
)