package com.sifat.slushflicks.api.home.tv.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvApiModel(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    @SerializedName("name")
    @Expose
    val title: String,
    @SerializedName("first_air_date")
    @Expose
    val releaseDate: String?,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Long>
)


/**
"original_name": "The Flash",
"genre_ids": [
18,
10765
],
"name": "The Flash",
"popularity": 220.57,
"origin_country": [
"US"
],
"vote_count": 4213,
"first_air_date": "2014-10-07",
"backdrop_path": "/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg",
"original_language": "en",
"id": 60735,
"vote_average": 7.2,
"overview": "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
"poster_path": "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg"
 * */
