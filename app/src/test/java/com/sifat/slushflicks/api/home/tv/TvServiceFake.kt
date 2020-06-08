package com.sifat.slushflicks.api.home.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.ApiTag.Companion.TV_CREDITS_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.details.model.ReviewListApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvShowDetailsApiModel
import com.sifat.slushflicks.utils.api.*
import com.sifat.slushflicks.utils.tvDetailsResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvServiceFake(private val gson: Gson) : TvService {
    var noYoutubeVideo: Boolean = false
    var errorCode: Int? = null

    override fun getTrendingTvShow(
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<TvListApiModel>> {
        val live = MutableLiveData<ApiResponse<TvListApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> getTvShowListSuccessResponse()
        }
        return live
    }

    override fun getTvShowList(
        collection: String,
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<TvListApiModel>> {
        val live = MutableLiveData<ApiResponse<TvListApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> getTvShowListSuccessResponse()
        }
        return live
    }

    override fun getTvShowDetails(
        tvShowId: Long,
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<TvShowDetailsApiModel>> {
        val live = MutableLiveData<ApiResponse<TvShowDetailsApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, TV_SHOW_API_TAG)
            UNAUTHORIZED -> getUnAuthResponse(gson, TV_SHOW_API_TAG)
            else -> getTvDetailsSuccessResponse()
        }
        return live
    }

    override fun getTvShowCredits(
        tvShowId: Long,
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<CreditsApiModel>> {
        val live = MutableLiveData<ApiResponse<CreditsApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, TV_CREDITS_API_TAG)
            UNAUTHORIZED -> getUnAuthResponse(gson, TV_CREDITS_API_TAG)
            else -> getCreditSuccessResponse()
        }
        return live
    }

    override fun getTvShowVideos(
        tvShowId: Long,
        season: Int,
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<VideoListApiModel>> {
        val live = MutableLiveData<ApiResponse<VideoListApiModel>>()
        live.value = when {
            RESOURCE_NOT_FOUND == errorCode -> getNoResResponse(gson, ApiTag.TV_VIDEO_API_TAG)
            UNAUTHORIZED == errorCode -> getUnAuthResponse(gson, ApiTag.TV_VIDEO_API_TAG)
            noYoutubeVideo -> getVideoSuccessResponseNoVideo()
            else -> getVideoSuccessResponse()
        }
        return live
    }

    override fun getRelatedTvShows(
        tvShowId: Long,
        relation: String,
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<TvListApiModel>> {
        val live = MutableLiveData<ApiResponse<TvListApiModel>>()
        live.value = when (errorCode) {
            RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> getTvShowListSuccessResponse()
        }
        return live
    }

    override fun getTvShowReviews(
        tvShowId: Long,
        apiKey: String,
        page: Int,
        tag: String
    ): Call<ReviewListApiModel> {
        return object : Call<ReviewListApiModel> {
            override fun enqueue(callback: Callback<ReviewListApiModel>) {

            }

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
        val credit = gson.fromJson(tvCastResponse, CreditsApiModel::class.java)
        return ApiSuccessResponse(
            data = credit
        )
    }

    private fun getVideoSuccessResponse(): ApiResponse<VideoListApiModel> {
        val videos = gson.fromJson(tvVideoResponse, VideoListApiModel::class.java)
        return ApiSuccessResponse(
            data = videos
        )
    }

    private fun getVideoSuccessResponseNoVideo(): ApiResponse<VideoListApiModel> {
        val videos = gson.fromJson(tvNoVideoResponse, VideoListApiModel::class.java)
        return ApiSuccessResponse(
            data = videos
        )
    }

    private fun getTvDetailsSuccessResponse(): ApiResponse<TvShowDetailsApiModel> {
        val tvModel = gson.fromJson(tvDetailsResponse, TvShowDetailsApiModel::class.java)
        return ApiSuccessResponse<TvShowDetailsApiModel>(
            data = tvModel
        )
    }

    private fun getTvShowListSuccessResponse(): ApiResponse<TvListApiModel> {
        val tvModel = gson.fromJson(tvSimilarResponse, TvListApiModel::class.java)
        return ApiSuccessResponse(
            data = tvModel
        )
    }
}