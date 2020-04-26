package com.example.slushflicks.api.home.movie

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiTag
import com.example.slushflicks.api.KEY_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Tag

interface MovieService {

    @GET("/movie/popular")
    fun getPopularMovies()

    @GET(TRENDING_MOVIE_URL)
    fun getTrendingMovies(
        @Query(KEY_API_KEY) apiKey: String,
        @Tag tag: String = ApiTag.TRENDING_API_TAG
    ): LiveData<ApiResponse<MovieListApiModel>>
}