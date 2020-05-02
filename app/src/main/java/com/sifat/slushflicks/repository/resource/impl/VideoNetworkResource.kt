package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.repository.resource.type.CacheUpdateResource
import com.sifat.slushflicks.ui.helper.isYoutubeModel
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.Job

class VideoNetworkResource(
    private val movieService: MovieService,
    private val dataManager: DataManager,
    private val requestModel: RequestModel,
    networkStateManager: NetworkStateManager
) : CacheUpdateResource<VideoListApiModel, VideoApiModel, String>(networkStateManager) {

    override fun createCall(): LiveData<ApiResponse<VideoListApiModel>> {
        return movieService.getMovieVideos(
            movieId = requestModel.movieId,
            apiKey = requestModel.apiKey
        )
    }

    override suspend fun updateLocalDb(cacheData: VideoApiModel?) {
        cacheData?.let { model ->
            dataManager.updateMovieDetails(model, requestModel.movieId)
        }
    }

    override fun setJob(job: Job) {

    }

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

    data class RequestModel(val apiKey: String, val movieId: Long)
}