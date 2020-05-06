package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.utils.api.NetworkStateManager

class TvVideoNetworkResource(
    private val tvService: TvService,
    private val dataManager: DataManager,
    private val requestModel: RequestModel,
    networkStateManager: NetworkStateManager
) : BaseVideoNetworkResource(networkStateManager) {
    override fun createCall(): LiveData<ApiResponse<VideoListApiModel>> {
        return tvService.getTvShowVideos(
            tvShowId = requestModel.tvShowId,
            season = requestModel.seasonNumber,
            apiKey = requestModel.apiKey
        )
    }

    override suspend fun updateLocalDb(cacheData: VideoApiModel?) {
        cacheData?.let { model ->
            dataManager.updateTvDetails(model, requestModel.tvShowId)
        }
    }

    data class RequestModel(val apiKey: String, val tvShowId: Long, val seasonNumber: Int)
}