package com.example.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiSuccessResponse
import com.example.slushflicks.api.home.movie.MovieListApiModel
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.model.MovieModel
import com.example.slushflicks.repository.resource.type.NetworkFirstSilentUpdateResource
import com.example.slushflicks.repository.resource.type.NetworkOnlyResource
import com.example.slushflicks.ui.helper.getMetaData
import com.example.slushflicks.ui.helper.getMovieList
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.ui.state.DataSuccessResponse
import com.example.slushflicks.utils.api.NetworkStateManager
import com.example.slushflicks.utils.livedata.AbsentLiveData
import kotlinx.coroutines.Job

class MovieListNetworkResource(
    private val movieService: MovieService,
    private val requestModel: RequestModel,
    networkStateManager: NetworkStateManager
) :
    NetworkOnlyResource<MovieListApiModel, List<MovieModel>, List<MovieModel>>(
        networkStateManager
    ) {

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<MovieListApiModel>) {
        /**
         * Change Api response to data response which viewModel will use to
         * show relevant information in view
         * */
        val dataSuccessResponse = DataSuccessResponse(
            data = getMovieList(response.data?.results),
            metaData = getMetaData(response.data),
            message = response.message
        )
        onCompleteJob(
            DataState.Success(
                dataResponse = dataSuccessResponse
            )
        )
    }

    override fun createCall(): LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getTrendingMovies(requestModel.apiKey, requestModel.page)
    }

    override fun loadFromCache(): LiveData<List<MovieModel>> {
        //TODO access db when on error loading from network
        return AbsentLiveData.create()
    }

    override suspend fun updateLocalDb(cacheData: List<MovieModel>?) {

    }

    override fun setJob(job: Job) {

    }

    data class RequestModel(val page: Int, val apiKey: String)

    override fun getAppDataFromCache(response: List<MovieModel>): DataSuccessResponse<List<MovieModel>> {
        return DataSuccessResponse(response)
    }
}