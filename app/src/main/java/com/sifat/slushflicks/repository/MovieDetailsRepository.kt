package com.sifat.slushflicks.repository

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.repository.resource.impl.DetailsNetworkResource
import com.sifat.slushflicks.repository.resource.impl.DetailsNetworkResource.RequestModel
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager

class MovieDetailsRepository(
    private val movieService: MovieService,
    private val dataManager: DataManager,
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager
) {
    fun getMovieDetails(movieId: Long): LiveData<DataState<MovieModel>> {
        return DetailsNetworkResource(
            dataManager = dataManager,
            movieService = movieService,
            request = RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }
}