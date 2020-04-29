package com.sifat.slushflicks.ui.home.movie.viewmodel

import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.repository.BaseMovieListRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.helper.getMovieListLoadingModels
import com.sifat.slushflicks.ui.helper.getMovieListModel
import com.sifat.slushflicks.ui.home.movie.state.MovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.MovieListEventState
import com.sifat.slushflicks.ui.home.movie.state.MovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewstate.MovieListViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState
import javax.inject.Inject

open class BaseMovieListViewModel
@Inject constructor(private val repository: BaseMovieListRepository) :
    BaseActionViewModel<MovieListDataAction, MovieListViewAction, MovieListViewState>() {
    override var viewState = MovieListViewState()

    fun handleEvent(event: MovieListEventState) {
        when (event) {
            is MovieListEventState.FetchMovieListEvent -> {
                fetchMovie(event)
            }
        }
    }

    private fun fetchMovie(event: MovieListEventState.FetchMovieListEvent) {
        if (!event.forceUpdate) {
            if (!viewState.movieList.isNullOrEmpty()) {
                sendMovieListSuccessAction()
                return
            }
        }
        sendMovieListLoadingAction()

        dataState.addSource(repository.getMovieList(viewState.nextPage())) { dataResponse ->
            dataState.value = MovieListDataAction.FetchMovieListDataAction(dataResponse)
        }
    }

    fun setDataAction(action: MovieListDataAction.FetchMovieListDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<List<MovieModelMinimal>> -> {
                dataState.dataResponse.metaData?.run {
                    viewState.currentPage = page
                }

                dataState.dataResponse.data?.let { movie ->
                    viewState.movieList = getMovieListModel(movie)
                    sendMovieListSuccessAction()
                }
            }
            is DataState.Error<List<MovieModelMinimal>> -> {
                sendMovieListErrorAction()
            }
        }
    }

    private fun sendMovieListErrorAction() {
        getAction().value = MovieListViewAction.FetchMovieListViewAction(
            ViewState.Error()
        )
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