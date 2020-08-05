package com.sifat.slushflicks.api.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_CREDITS_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_DETAIL_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_VIDEO_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.details.model.ReviewListApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieDetailsApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.utils.api.*
import com.sifat.slushflicks.utils.movieDetailsResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieServiceFake(val gson: Gson) : MovieService {
    var noYoutubeVideo: Boolean = false
    var errorCode: Int? = null

    override fun getMoviesList(
        collection: String,
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<MovieListApiModel>> {
        val live = MutableLiveData<ApiResponse<MovieListApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> getMovieListSuccessResponse(page)
        }
        return live
    }

    override fun getTrendingMovies(
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<MovieListApiModel>> {
        val live = MutableLiveData<ApiResponse<MovieListApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> getSimilarMovieListSuccessResponse()
        }
        return live
    }

    override fun getMovieDetails(
        movieId: Long,
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<MovieDetailsApiModel>> {
        val live = MutableLiveData<ApiResponse<MovieDetailsApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, MOVIE_DETAIL_API_TAG)
            UNAUTHORIZED -> getUnAuthResponse(gson, MOVIE_DETAIL_API_TAG)
            else -> getMovieDetailsSuccessResponse()
        }
        return live
    }

    override fun getMovieVideos(
        movieId: Long,
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<VideoListApiModel>> {
        val live = MutableLiveData<ApiResponse<VideoListApiModel>>()
        live.value = when {
            RESOURCE_NOT_FOUND == errorCode -> getNoResResponse(gson, MOVIE_VIDEO_API_TAG)
            UNAUTHORIZED == errorCode -> getUnAuthResponse(gson, MOVIE_VIDEO_API_TAG)
            noYoutubeVideo -> getVideoSuccessResponseNoVideo()
            else -> getVideoSuccessResponse()
        }
        return live
    }

    override fun getMovieCredits(
        movieId: Long,
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<CreditsApiModel>> {
        val live = MutableLiveData<ApiResponse<CreditsApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, MOVIE_CREDITS_API_TAG)
            UNAUTHORIZED -> getUnAuthResponse(gson, MOVIE_CREDITS_API_TAG)
            else -> getCreditSuccessResponse()
        }
        return live
    }

    override fun getRelatedMovies(
        movieId: Long,
        relation: String,
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<MovieListApiModel>> {
        val live = MutableLiveData<ApiResponse<MovieListApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> getSimilarMovieListSuccessResponse()
        }
        return live
    }

    override fun getMovieReviews(
        movieId: Long,
        apiKey: String,
        page: Int,
        tag: String
    ): Call<ReviewListApiModel> {
        return object : Call<ReviewListApiModel> {
            override fun enqueue(callback: Callback<ReviewListApiModel>) {}

            override fun isExecuted() = true

            override fun clone(): Call<ReviewListApiModel> {
                return this
            }

            override fun isCanceled() = false

            override fun cancel() {}

            override fun execute(): Response<ReviewListApiModel>? {
                return null
            }

            override fun request(): Request {
                return Request.Builder().build()
            }
        }
    }

    private fun getCreditSuccessResponse(): ApiResponse<CreditsApiModel>? {
        val credit = gson.fromJson(movieCastResponse, CreditsApiModel::class.java)
        return ApiSuccessResponse(
            data = credit
        )
    }

    private fun getVideoSuccessResponse(): ApiResponse<VideoListApiModel> {
        val videos = gson.fromJson(movieVideoResponse, VideoListApiModel::class.java)
        return ApiSuccessResponse(
            data = videos
        )
    }

    private fun getVideoSuccessResponseNoVideo(): ApiResponse<VideoListApiModel> {
        val videos = gson.fromJson(movieNoVideoResponse, VideoListApiModel::class.java)
        return ApiSuccessResponse(
            data = videos
        )
    }

    private fun getMovieDetailsSuccessResponse(): ApiResponse<MovieDetailsApiModel> {
        val movieModel = gson.fromJson(movieDetailsResponse, MovieDetailsApiModel::class.java)
        return ApiSuccessResponse(
            data = movieModel
        )
    }

    private fun getSimilarMovieListSuccessResponse(): ApiResponse<MovieListApiModel> {
        val movieModel = gson.fromJson(movieSimilarResponse, MovieListApiModel::class.java)
        return ApiSuccessResponse(
            data = movieModel
        )
    }

    private fun getMovieListSuccessResponse(page: Int = 1): ApiResponse<MovieListApiModel> {
        val movieModel = gson.fromJson(
            if (page == 1) moviePage1 else if (page == 2) moviePage2 else moviePageInvalid
            , MovieListApiModel::class.java
        )
        return ApiSuccessResponse(
            data = movieModel
        )
    }
}