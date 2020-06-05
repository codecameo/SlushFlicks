package com.sifat.slushflicks.api.home.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.ApiTag.Companion.TV_CREDITS_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.details.model.ReviewListApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvShowDetailsApiModel
import com.sifat.slushflicks.utils.api.getNoResResponse
import com.sifat.slushflicks.utils.api.getUnAuthResponse
import com.sifat.slushflicks.utils.api.tvCastResponse
import com.sifat.slushflicks.utils.livedata.AbsentLiveData
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvServiceFake(val gson: Gson) : TvService {
    var errorCode: Int? = null

    override fun getTrendingTvShow(
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<TvListApiModel>> {
        return AbsentLiveData.create()
    }

    override fun getTvShowList(
        collection: String,
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<TvListApiModel>> {
        return AbsentLiveData.create()
    }

    override fun getTvShowDetails(
        tvShowId: Long,
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<TvShowDetailsApiModel>> {
        return AbsentLiveData.create()
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
        return AbsentLiveData.create()
    }

    override fun getRelatedTvShows(
        tvShowId: Long,
        relation: String,
        apiKey: String,
        page: Int,
        tag: String
    ): LiveData<ApiResponse<TvListApiModel>> {
        return AbsentLiveData.create()
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
        return ApiSuccessResponse<CreditsApiModel>(
            data = credit
        )
    }
}