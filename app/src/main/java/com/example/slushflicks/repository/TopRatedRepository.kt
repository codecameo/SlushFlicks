package com.example.slushflicks.repository

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiTag.Companion.TOP_RATED_API_TAG
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.model.MovieModelMinimal
import com.example.slushflicks.repository.resource.impl.MovieListNetworkResource
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.utils.Label.Companion.TOP_RATED_LABEL
import com.example.slushflicks.utils.api.NetworkStateManager

class TopRatedRepository(
    movieService: MovieService,
    apiKey: String,
    dataManager: DataManager,
    networkStateManager: NetworkStateManager
) : BaseMovieListRepository(movieService, apiKey, dataManager, networkStateManager) {

    override fun getMovieList(nextPage: Int): LiveData<DataState<List<MovieModelMinimal>>> {
        val requestModel = MovieListNetworkResource.RequestModel(
            page = nextPage,
            apiKey = apiKey,
            apiTag = TOP_RATED_API_TAG
        )
        val movieListNetworkResource = MovieListNetworkResource(
            requestModel = requestModel,
            movieService = movieService,
            networkStateManager = networkStateManager,
            dataManager = dataManager,
            collection = TOP_RATED_LABEL
        )
        return movieListNetworkResource.asLiveData()
    }
}