package com.sifat.slushflicks.api.details.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoListApiModel(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("results")
    @Expose
    val results: List<VideoApiModel>
)