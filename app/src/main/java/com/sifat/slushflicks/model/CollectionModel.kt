package com.sifat.slushflicks.model

import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.utils.EMPTY_STRING

data class CollectionModel(
    @SerializedName("name")
    var name: String = EMPTY_STRING,
    @SerializedName("label")
    var label: String = EMPTY_STRING,
    @SerializedName("isEnable")
    var isEnable: Boolean = false
)