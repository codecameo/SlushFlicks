package com.sifat.slushflicks.api.home.genre.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.model.GenreModel

data class GenreListApiModel(
    @SerializedName("genres")
    @Expose
    val genres : List<GenreModel>
)