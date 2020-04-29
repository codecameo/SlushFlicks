package com.sifat.slushflicks.ui.home.movie.viewmodel

import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.repository.BaseMovieListRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.helper.getMovieListLoadingModels
import com.sifat.slushflicks.ui.helper.getMovieListModel
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction.FetchMovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState.FetchMovieListEvent
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewstate.MovieListViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.ViewState
import javax.inject.Inject

open class BaseMovieListViewModel
@Inject constructor(private val repository: BaseMovieListRepository) :
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

        dataState.addSource(repository.getMovieList(viewState.nextPage())) { dataResponse ->
            dataState.value = FetchMovieListDataAction(dataResponse)
        }
    }

    fun setDataAction(action: FetchMovieListDataAction) {
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
            is Error<List<MovieModelMinimal>> -> {
                sendMovieListErrorAction(dataState)
            }
        }
    }

    private fun sendMovieListErrorAction(dataState: Error<List<MovieModelMinimal>>) {
        getAction().value = MovieListViewAction.FetchMovieListViewAction(
            ViewState.Error(
                errorMessage = dataState.dataResponse.errorMessage
            )
        )
    }

    /**
     * Send movie list to the view
     * */
    private fun sendMovieListSuccessAction() {
        getAction().value = MovieListViewAction.FetchMovieListViewAction(
            ViewState.Success(
                viewState.movieList
            )
        )
    }

    /**
     * Send loading action to the view
     * */
    private fun sendMovieListLoadingAction() {
        getAction().value = MovieListViewAction.FetchMovieListViewAction(
            ViewState.Loading(
                getMovieListLoadingModels()
            )
        )
    }
}