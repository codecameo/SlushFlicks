package com.sifat.slushflicks.api.home.movie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieListApiModel(
    @SerializedName("page")
    @Expose
    val page: Int,
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int,
    @SerializedName("total_results")
    @Expose
    val totalResults: Long,
    @SerializedName("results")
    @Expose
    val results: List<MovieApiModel>
)