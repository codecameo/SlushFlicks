package com.sifat.slushflicks.api.home.search

import com.sifat.slushflicks.api.ApiEndPoint
import com.sifat.slushflicks.api.ApiRequest
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Tag

interface SearchService {

    @GET(ApiEndPoint.SEARCH_MOVIE_URL)
    fun getSearchMovies(
        @Query(ApiRequest.QUERY_KEY_SEARCH) query: String,
        @Query(ApiRequest.QUERY_KEY_API_KEY) apiKey: String,
        @Query(ApiRequest.QUERY_KEY_PAGE) page: Int,
        @Tag tag: String = ApiTag.MOVIE_SEARCH_API_TAG
    ): Call<MovieListApiModel>

    @GET(ApiEndPoint.SEARCH_TV_SHOW_URL)
    fun getSearchTvShows(
        @Query(ApiRequest.QUERY_KEY_SEARCH) query: String,
        @Query(ApiRequest.QUERY_KEY_API_KEY) apiKey: String,
        @Query(ApiRequest.QUERY_KEY_PAGE) page: Int,
        @Tag tag: String = ApiTag.TV_SHOW_SEARCH_API_TAG
    ): Call<TvListApiModel>
}