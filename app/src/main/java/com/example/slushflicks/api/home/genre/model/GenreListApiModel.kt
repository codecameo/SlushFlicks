package com.example.slushflicks.api.home.genre.model

import com.example.slushflicks.model.GenreModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreListApiModel(
    @SerializedName("genres")
    @Expose
    val genres : List<GenreModel>
)