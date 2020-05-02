package com.sifat.slushflicks.api.details.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoApiModel(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("key")
    @Expose
    val key: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("site")
    @Expose
    val site: String
)


/**
"id": "5dad5e3afea6e30011ada0ab",
"iso_639_1": "en",
"iso_3166_1": "US",
"key": "F95Fk255I4M",
"name": "BLOODSHOT – International Trailer",
"site": "YouTube",
"size": 1080,
"type": "Trailer"
 * */