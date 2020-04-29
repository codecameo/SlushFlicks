package com.sifat.slushflicks.api.home.movie

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.sifat.slushflicks.api.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.sifat.slushflicks.api.ApiRequest.Companion.QUERY_KEY_PAGE
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Tag

interface MovieService {

    @GET("movie/{collection}")
    fun getMoviesList(
        @Path("collection") collection: String,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String
    ): LiveData<ApiResponse<MovieListApiModel>>

    @GET(TRENDING_MOVIE_URL)
    fun getTrendingMovies(
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page : Int,
        @Tag tag: String
    ): LiveData<ApiResponse<MovieListApiModel>>
}