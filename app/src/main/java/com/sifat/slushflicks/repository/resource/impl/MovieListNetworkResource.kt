package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiErrorResponse
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.repository.resource.type.NetworkOnlyResource
import com.sifat.slushflicks.ui.helper.getCollectionModels
import com.sifat.slushflicks.ui.helper.getMetaData
import com.sifat.slushflicks.ui.helper.getMovieList
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class MovieListNetworkResource(
    protected val movieService: MovieService,
    protected val requestModel: RequestModel,
    protected val dataManager: DataManager,
    private val jobManager: JobManager,
    networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NetworkOnlyResource<MovieListApiModel, List<MovieModel>, Int>(
    networkStateManager, dispatcher
) {

    override fun createCall(): LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getMoviesList(
            apiKey = requestModel.apiKey,
            page = requestModel.page,
            tag = requestModel.apiTag,
            collection = requestModel.collection
        )
    }

    override suspend fun updateLocalDb(cacheData: List<MovieModel>?) {
        if (!cacheData.isNullOrEmpty()) {
            // Insert with Ignore strategy
            dataManager.softInsertMovie(cacheData)
            val collectionModels =
                getCollectionModels(cacheData, requestModel.collection, requestModel.page)
            if (requestModel.page == 1) {
                dataManager.insertNewMovieCollection(requestModel.collection, collectionModels)
            } else {
                dataManager.addMovieCollection(collectionModels)
            }
        }
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<MovieListApiModel>) {
        /**
         * Change Api response to data response which viewModel will use to
         * show relevant information in view
         * */
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(
            DataState.Success(
                getAppDataSuccessResponse(dataSuccessResponse)
            )
        )
    }

    override fun setJob(job: Job) {
        jobManager.addJob(requestModel.collection, job)
    }

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<List<MovieModel>>): DataSuccessResponse<Int> {
        return DataSuccessResponse(
            data = response.data?.size,
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

    override fun getInternalErrorResponse() = ApiErrorResponse<MovieListApiModel>(
        statusCode = INTERNAL_ERROR,
        apiTag = requestModel.apiTag
    )

    data class RequestModel(
        val page: Int,
        val apiKey: String,
        val apiTag: String,
        val collection: String
    )
}