package com.example.slushflicks.repository

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.model.MovieModel
import com.example.slushflicks.repository.resource.impl.MovieListNetworkResource
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.utils.api.NetworkStateManager

/**
 * TODO Create repository interface and Implement that
 * */
class TrendingRepository(
    private val movieService: MovieService,
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager
) {

    fun getMovieList(nextPage: Int): LiveData<DataState<List<MovieModel>>> {

        val requestModel = MovieListNetworkResource.RequestModel(
            page = nextPage,
            apiKey = apiKey
        )
        val movieListNetworkResource = MovieListNetworkResource(
            requestModel = requestModel,
            movieService = movieService,
            networkStateManager = networkStateManager
        )
        return movieListNetworkResource.asLiveData()
    }
}