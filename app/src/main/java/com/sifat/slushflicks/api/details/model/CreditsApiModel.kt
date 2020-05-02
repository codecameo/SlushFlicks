package com.sifat.slushflicks.api.details.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.model.CastModel

data class CreditsApiModel(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("cast")
    @Expose
    val casts: List<CastModel>
)