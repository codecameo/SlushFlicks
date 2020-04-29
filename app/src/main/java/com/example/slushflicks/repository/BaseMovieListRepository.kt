package com.example.slushflicks.repository

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.model.MovieModelMinimal
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.utils.api.NetworkStateManager

abstract class BaseMovieListRepository(
    protected val movieService: MovieService,
    protected val apiKey: String,
    protected val dataManager: DataManager,
    protected val networkStateManager: NetworkStateManager
) {

    abstract fun getMovieList(nextPage: Int): LiveData<DataState<List<MovieModelMinimal>>>
}