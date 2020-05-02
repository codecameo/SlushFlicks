package com.sifat.slushflicks.api.home.movie

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_COLLECTION_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_CREDITS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_DETAILS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_RELATED_MOVIE_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_VIDEOS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_COLLECTION
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_MOVIE_ID
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_MOVIE_RELATION_TYPE
import com.sifat.slushflicks.api.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.sifat.slushflicks.api.ApiRequest.Companion.QUERY_KEY_PAGE
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_CREDITS_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_VIDEO_API_TAG
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieDetailsApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Tag

interface MovieService {

    @GET(MOVIE_COLLECTION_URL)
    fun getMoviesList(
        @Path(PATH_COLLECTION) collection: String,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String
    ): LiveData<ApiResponse<MovieListApiModel>>

    @GET(TRENDING_MOVIE_URL)
    fun getTrendingMovies(
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String
    ): LiveData<ApiResponse<MovieListApiModel>>

    @GET(MOVIE_DETAILS_URL)
    fun getMovieDetails(
        @Path(PATH_MOVIE_ID) movieId: Long,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = ApiTag.MOVIE_DETAIL_API_TAG
    ): LiveData<ApiResponse<MovieDetailsApiModel>>

    @GET(MOVIE_VIDEOS_URL)
    fun getMovieVideos(
        @Path(PATH_MOVIE_ID) movieId: Long,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = MOVIE_VIDEO_API_TAG
    ): LiveData<ApiResponse<VideoListApiModel>>

    @GET(MOVIE_CREDITS_URL)
    fun getMovieCredits(
        @Path(PATH_MOVIE_ID) movieId: Long,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = MOVIE_CREDITS_API_TAG
    ): LiveData<ApiResponse<CreditsApiModel>>

    @GET(MOVIE_RELATED_MOVIE_URL)
    fun getRelatedMovies(
        @Path(PATH_MOVIE_ID) movieId: Long,
        @Path(PATH_MOVIE_RELATION_TYPE) relation: String,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String
    ): LiveData<ApiResponse<MovieListApiModel>>

    /*@GET(MOVIE_SIMILAR_URL)
    fun getSimilarMovies(
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String = MOVIE_SIMILAR_API_TAG
    ): LiveData<ApiResponse<MovieListApiModel>>*/
}