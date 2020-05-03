package com.sifat.slushflicks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewModel(
    @SerializedName("author")
    @Expose
    val author: String,
    @SerializedName("content")
    @Expose
    val content: String,
    @SerializedName("id")
    @Expose
    val id: String
)