package com.example.slushflicks.api.home.movie

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.example.slushflicks.api.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.example.slushflicks.api.ApiRequest.Companion.QUERY_KEY_PAGE
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiTag
import com.example.slushflicks.api.home.movie.model.MovieListApiModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Tag

interface MovieService {

    @GET("/movie/popular")
    fun getPopularMovies()

    @GET(TRENDING_MOVIE_URL)
    fun getTrendingMovies(
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page : Int,
        @Tag tag: String = ApiTag.TRENDING_API_TAG
    ): LiveData<ApiResponse<MovieListApiModel>>
}