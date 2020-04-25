package com.example.slushflicks.ui.home.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.ui.base.BaseActionViewModel
import com.example.slushflicks.ui.base.BaseViewModel
import com.example.slushflicks.ui.base.BaseViewState
import com.example.slushflicks.ui.helper.getMovieListLoadingModels
import com.example.slushflicks.ui.helper.getViewMovieList
import com.example.slushflicks.ui.home.adapter.model.MovieListModel
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction.FetchMovieListDataAction
import com.example.slushflicks.ui.home.movie.state.MovieListEventState
import com.example.slushflicks.ui.home.movie.state.MovieListEventState.FetchMovieListEvent
import com.example.slushflicks.ui.home.movie.state.MovieListViewAction
import com.example.slushflicks.ui.home.movie.state.viewstate.MovieListViewState
import com.example.slushflicks.ui.model.MovieModel
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.ui.state.ViewState
import javax.inject.Inject

@HomeScope
class TrendingViewModel
@Inject constructor() :
    BaseActionViewModel<MovieListDataAction, MovieListViewAction, MovieListViewState>() {
    override var viewState = MovieListViewState()

    fun handleEvent(event: MovieListEventState) {
        when (event) {
            is FetchMovieListEvent -> {
                getAction().value = MovieListViewAction.FetchMovieListViewAction(
                    ViewState.Loading(
                        getMovieListLoadingModels()
                    )
                )
            }
        }
    }

    fun setDataAction(action: FetchMovieListDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<List<MovieModel>> -> {
                dataState.apiSuccess.data?.let { movie ->
                    viewState.movieList = getViewMovieList(movie)
                    getAction().value = MovieListViewAction.FetchMovieListViewAction(
                        ViewState.Success(
                            viewState.movieList
                        )
                    )
                }
            }
        }
    }
}