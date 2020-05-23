package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TrendingMovieListResource(
    movieService: MovieService,
    requestModel: RequestModel,
    dataManager: DataManager,
    collection: String,
    networkStateManager: NetworkStateManager,
    jobManager: JobManager,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieListNetworkResource(
    movieService = movieService,
    requestModel = requestModel,
    collection = collection,
    dataManager = dataManager,
    networkStateManager = networkStateManager,
    jobManager = jobManager,
    dispatcher = dispatcher
) {

    override fun createCall(): LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getTrendingMovies(
            apiKey = requestModel.apiKey,
            tag = requestModel.apiTag,
            page = requestModel.page
        )
    }
}