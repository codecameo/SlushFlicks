package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.resource.type.NetworkOnlyResource
import com.sifat.slushflicks.ui.helper.getMetaData
import com.sifat.slushflicks.ui.helper.getMovieList
import com.sifat.slushflicks.ui.helper.getMovieMinimalModel
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SimilarMoviesNetworkResource(
    private val movieService: MovieService,
    private val dataManager: DataManager,
    private val requestModel: RequestModel,
    private val jobManager: JobManager,
    networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NetworkOnlyResource<MovieListApiModel, List<MovieModel>, List<ShowModelMinimal>>(
    networkStateManager, dispatcher
) {
    override fun createCall(): LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getRelatedMovies(
            apiKey = requestModel.apiKey,
            relation = requestModel.relationType,
            movieId = requestModel.movieId,
            page = 1,
            tag = requestModel.apiTag
        )
    }

    override suspend fun updateLocalDb(cacheData: List<MovieModel>?) {
        /** Skip caching, since it's taking too much memory in the device */
        /*if (!cacheData.isNullOrEmpty()) {
            // Insert with Ignore strategy
            dataManager.softInsertMovie(cacheData)
        }*/
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<MovieListApiModel>) {
        /**
         * Change Api response to data response which viewModel will use to
         * show relevant information in view
         * */
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(
            DataState.Success<List<ShowModelMinimal>>(
                getAppDataSuccessResponse(dataSuccessResponse)
            )
        )
    }

    override fun setJob(job: Job) {
        jobManager.addJob(requestModel.apiTag, job)
    }

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<List<MovieModel>>): DataSuccessResponse<List<ShowModelMinimal>> {
        return DataSuccessResponse(
            data = getMovieMinimalModel(response.data),
            metaData = response.metaData,
            message = response.message
        )
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<MovieListApiModel>): DataSuccessResponse<List<MovieModel>> {
        return DataSuccessResponse(
            data = getMovieList(response.data?.results, dataManager.getGenres()),
            metaData = getMetaData(response.data),
            message = response.message
        )
    }

    data class RequestModel(
        val apiKey: String,
        val apiTag: String,
        val movieId: Long,
        val relationType: String
    )
}