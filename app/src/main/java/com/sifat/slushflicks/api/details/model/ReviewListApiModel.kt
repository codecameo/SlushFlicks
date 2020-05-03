package com.sifat.slushflicks.api.details.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.model.ReviewModel

data class ReviewListApiModel(
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
    val results: List<ReviewModel>
)