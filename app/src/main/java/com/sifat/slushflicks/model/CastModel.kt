package com.sifat.slushflicks.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CastModel(
    @SerializedName("cast_id")
    @Expose
    val castId: Int,
    @SerializedName("character")
    @Expose
    val character: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("order")
    @Expose
    val order: Int,
    @SerializedName("profile_path")
    @Expose
    val profileImage: String?
)

/**
"cast_id": 34,
"character": "Thomas A. Anderson / Neo",
"credit_id": "52fe425bc3a36847f80181c1",
"gender": 2,
"id": 6384,
"name": "Keanu Reeves",
"order": 0,
"profile_path": "/bOlYWhVuOiU6azC4Bw6zlXZ5QTC.jpg"
 * */