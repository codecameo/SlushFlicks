package com.sifat.slushflicks.repository.resource.impl

import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.repository.resource.type.CacheUpdateResource
import com.sifat.slushflicks.ui.helper.isYoutubeModel
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseVideoNetworkResource(
    networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher
) : CacheUpdateResource<VideoListApiModel, VideoApiModel, String>(networkStateManager, dispatcher) {

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<VideoApiModel>): DataSuccessResponse<String> {
        return DataSuccessResponse(
            data = response.data?.key
        )
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<VideoListApiModel>): DataSuccessResponse<VideoApiModel> {
        var videoModel: VideoApiModel? = null
        response.data?.let { apiModel ->
            if (apiModel.results.isNotEmpty()) {
                for (model in apiModel.results) {
                    if (isYoutubeModel(model)) {
                        videoModel = model
                        break
                    }
                }
            }
        }

        return DataSuccessResponse(
            data = videoModel
        )
    }
}