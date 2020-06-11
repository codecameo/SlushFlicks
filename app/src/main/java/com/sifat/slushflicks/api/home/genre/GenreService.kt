package com.sifat.slushflicks.api.home.genre

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiEndPoint
import com.sifat.slushflicks.api.ApiEndPoint.Companion.GENRES_TV_URL
import com.sifat.slushflicks.api.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_GENRE_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_GENRE_API_TAG
import com.sifat.slushflicks.api.home.genre.model.GenreListApiModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Tag

interface GenreService {

    @GET(GENRES_TV_URL)
    fun getTvGenre(
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = TV_GENRE_API_TAG
    ): LiveData<ApiResponse<GenreListApiModel>>

    @GET(ApiEndPoint.GENRES_MOVIE_URL)
    fun getMovieGenre(
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = MOVIE_GENRE_API_TAG
    ): LiveData<ApiResponse<GenreListApiModel>>
}