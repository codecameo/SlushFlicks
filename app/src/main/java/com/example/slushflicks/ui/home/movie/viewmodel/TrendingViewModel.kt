package com.example.slushflicks.ui.home.movie.viewmodel

import com.example.slushflicks.api.ApiSuccessResponse
import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.repository.TrendingRepository
import com.example.slushflicks.ui.base.BaseActionViewModel
import com.example.slushflicks.ui.helper.getMovieListLoadingModels
import com.example.slushflicks.ui.helper.getMovieListModel
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction.FetchMovieListDataAction
import com.example.slushflicks.ui.home.movie.state.MovieListEventState
import com.example.slushflicks.ui.home.movie.state.MovieListEventState.FetchMovieListEvent
import com.example.slushflicks.ui.home.movie.state.MovieListViewAction
import com.example.slushflicks.ui.home.movie.state.viewstate.MovieListViewState
import com.example.slushflicks.model.MovieModel
import com.example.slushflicks.ui.helper.getMetaData
import com.example.slushflicks.ui.helper.getMovieList
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.ui.state.DataSuccessResponse
import com.example.slushflicks.ui.state.ViewState
import javax.inject.Inject

@HomeScope
class TrendingViewModel
@Inject constructor(private val trendingRepository: TrendingRepository) :
    BaseActionViewModel<MovieListDataAction, MovieListViewAction, MovieListViewState>() {
    override var viewState = MovieListViewState()

    fun handleEvent(event: MovieListEventState) {
        when (event) {
            is FetchMovieListEvent -> {
                fetchMovie(event)
            }
        }
    }

    private fun fetchMovie(event: FetchMovieListEvent) {
        if (!event.forceUpdate) {
            if (!viewState.movieList.isNullOrEmpty()) {
                sendMovieListSuccessAction()
                return
            }
        }
        sendMovieListLoadingAction()

        dataState.addSource(trendingRepository.getMovieList(viewState.nextPage())) { dataResponse ->
            dataState.value = dataResponse
        }
    }

    fun setDataAction(action: FetchMovieListDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<List<MovieModel>> -> {
                dataState.dataResponse.metaData?.run {
                    viewState.currentPage = page
                }

                dataState.dataResponse.data?.let { movie ->
                    viewState.movieList = getMovieListModel(movie)
                    sendMovieListSuccessAction()
                }
            }
        }
    }

    private fun sendMovieListSuccessAction() {
        getAction().value = MovieListViewAction.FetchMovieListViewAction(
            ViewState.Success(
                viewState.movieList
            )
        )
    }

    private fun sendMovieListLoadingAction() {
        getAction().value = MovieListViewAction.FetchMovieListViewAction(
            ViewState.Loading(
                getMovieListLoadingModels()
            )
        )
    }
}