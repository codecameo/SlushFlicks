package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.api.home.movie.model.MovieDetailsApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.repository.resource.type.CacheFirstNetworkUpdateResource
import com.sifat.slushflicks.ui.helper.getMovieDetails
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getDistinct
import kotlinx.coroutines.Job

class MovieDetailsNetworkResource(
    private val dataManager: DataManager,
    private val movieService: MovieService,
    private val request: RequestModel,
    private val jobManager: JobManager,
    networkStateManager: NetworkStateManager
) : CacheFirstNetworkUpdateResource<MovieDetailsApiModel, MovieModel, MovieModel>(
    networkStateManager
) {

    /**
     * In case App is representing a different model than the database model, the conversion can take place here,
     * In this case app and database is using the same [MovieModel], thus conversion is not needed
     * */
    override fun getAppDataSuccessResponse(response: DataSuccessResponse<MovieModel>): DataSuccessResponse<MovieModel> {
        return response
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<MovieDetailsApiModel>): DataSuccessResponse<MovieModel> {
        return DataSuccessResponse(
            data = getMovieDetails(response.data),
            message = response.message
        )
    }

    override fun createCall(): LiveData<ApiResponse<MovieDetailsApiModel>> {
        return movieService.getMovieDetails(
            apiKey = request.apiKey,
            movieId = request.movieId
        )
    }

    override suspend fun updateLocalDb(cacheData: MovieModel?) {
        cacheData?.let { model ->
            dataManager.updateMovieDetails(model)
        }
    }

    override fun setJob(job: Job) {
        jobManager.addJob(TAG, job = job)
    }

    data class RequestModel(val apiKey: String, val movieId: Long)

    override fun loadFromCache(): LiveData<MovieModel> {
        return dataManager.getMovieDetails(request.movieId).getDistinct()
    }

    companion object {
        private const val TAG = "MovieDetailsNetworkReso"
    }
}