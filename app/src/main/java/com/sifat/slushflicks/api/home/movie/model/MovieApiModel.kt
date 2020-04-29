package com.sifat.slushflicks.api.home.movie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieApiModel(
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
    val releaseDate: String,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,
    @SerializedName("original_title")
    @Expose
    val originalTitle: String,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("adult")
    @Expose
    val adult: Boolean,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Long>
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
