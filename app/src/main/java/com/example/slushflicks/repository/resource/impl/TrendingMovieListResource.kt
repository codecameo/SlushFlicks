package com.example.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.api.home.movie.model.MovieListApiModel
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.utils.api.NetworkStateManager

class TrendingMovieListResource(
    movieService: MovieService,
    requestModel: MovieListNetworkResource.RequestModel,
    dataManager: DataManager,
    collection: String,
    networkStateManager: NetworkStateManager
) : MovieListNetworkResource(
    movieService,
    requestModel,
    dataManager,
    collection,
    networkStateManager
) {

    override fun createCall(): LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getTrendingMovies(
            apiKey = requestModel.apiKey,
            tag = requestModel.apiTag,
            page = requestModel.page
        )
    }
}