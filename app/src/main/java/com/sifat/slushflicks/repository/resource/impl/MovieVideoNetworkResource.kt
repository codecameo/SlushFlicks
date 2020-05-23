package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MovieVideoNetworkResource(
    private val movieService: MovieService,
    private val dataManager: DataManager,
    private val requestModel: RequestModel,
    private val jobManager: JobManager,
    networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseVideoNetworkResource(networkStateManager, dispatcher) {

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
        jobManager.addJob(TAG, job)
    }

    data class RequestModel(val apiKey: String, val movieId: Long)

    companion object {
        private const val TAG = "MovieVideoNetworkResour"
    }
}