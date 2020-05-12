package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.utils.api.NetworkStateManager

class TrendingMovieListResource(
    movieService: MovieService,
    requestModel: RequestModel,
    dataManager: DataManager,
    collection: String,
    networkStateManager: NetworkStateManager,
    jobManager: JobManager
) : MovieListNetworkResource(
    movieService = movieService,
    requestModel = requestModel,
    collection = collection,
    dataManager = dataManager,
    networkStateManager = networkStateManager,
    jobManager = jobManager
) {

    override fun createCall(): LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getTrendingMovies(
            apiKey = requestModel.apiKey,
            tag = requestModel.apiTag,
            page = requestModel.page
        )
    }
}