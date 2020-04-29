package com.sifat.slushflicks.repository

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager

abstract class BaseMovieListRepository(
    protected val movieService: MovieService,
    protected val apiKey: String,
    protected val dataManager: DataManager,
    protected val networkStateManager: NetworkStateManager
) {

    abstract fun getMovieList(nextPage: Int): LiveData<DataState<List<MovieModelMinimal>>>
}