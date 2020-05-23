package com.sifat.slushflicks.ui.home.movie.viewmodel

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.movie.MovieListRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState.FetchMovieListEvent
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction.FetchCacheMovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewstate.MovieListViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.ViewState

open class BaseMovieListViewModel
    (private val repository: MovieListRepository) :
    BaseActionViewModel<MovieListViewAction, MovieListViewState>() {
    override val viewState by lazy {
        MovieListViewState()
    }

    fun handleEvent(event: MovieListEventState) {
        when (event) {
            is FetchMovieListEvent -> {
                fetchMovie(event)
            }
        }
    }

    private fun fetchMovie(event: FetchMovieListEvent) {
        if (!event.forceUpdate) {
            if (!viewState.showList.isNullOrEmpty()) {
                sendMovieListSuccessAction()
                return
            }
        }
        sendMovieListLoadingAction()
        val initialUpdate = repository.getMovieList(viewState.nextPage())
        dataState.addSource(initialUpdate) { dataResponse ->
            dataState.removeSource(initialUpdate)
            setListMeta(dataResponse)
        }
        fetchFromCache()
    }

    private fun fetchFromCache() {
        dataState.addSource(repository.getPagingMovieList(boundaryCallback)) { dataResponse ->
            setMovieList(dataResponse)
        }
    }

    private fun updateCache() {
        if (viewState.currentPage < viewState.totalPage) {
            val networkUpdate = repository.getMovieList(viewState.nextPage())
            dataState.addSource(networkUpdate) { dataResponse ->
                dataState.removeSource(networkUpdate)
                setListMeta(dataResponse)
            }
        }
    }

    private fun setMovieList(dataState: DataState<PagedList<ShowModelMinimal>>) {
        when (dataState) {
            is DataState.Success<PagedList<ShowModelMinimal>> -> {
                dataState.dataResponse.data?.let { movie ->
                    viewState.showList = movie
                    sendMovieListSuccessAction()
                }
            }
            is Error<PagedList<ShowModelMinimal>> -> {
                sendMovieListErrorAction(dataState)
            }
        }
    }

    private fun setListMeta(dataState: DataState<Int>) {
        when (dataState) {
            is DataState.Success<Int> -> {
                dataState.dataResponse.metaData?.run {
                    viewState.totalPage = totalPage
                    viewState.currentPage = page
                }
            }
            is Error<Int> -> {
                sendMovieListUpdateErrorAction(dataState)
            }
        }
    }

    private fun sendMovieListErrorAction(dataState: Error<PagedList<ShowModelMinimal>>) {
        getAction().value = FetchCacheMovieListViewAction(
            ViewState.Error(
                errorMessage = dataState.dataResponse.errorMessage
            )
        )
    }

    private fun sendMovieListUpdateErrorAction(dataState: Error<Int>) {
        getAction().value = MovieListViewAction.FetchNetworkMovieListViewAction(
            ViewState.Error(
                errorMessage = dataState.dataResponse.errorMessage
            )
        )
    }

    /**
     * Send movie list to the view
     * */
    private fun sendMovieListSuccessAction() {
        getAction().value = FetchCacheMovieListViewAction(
            ViewState.Success(
                viewState.showList
            )
        )
    }

    /**
     * Send loading action to the view
     * */
    private fun sendMovieListLoadingAction() {
        getAction().value = FetchCacheMovieListViewAction(
            ViewState.Loading(
                //getMovieListLoadingModels()
            )
        )
    }

    private val boundaryCallback = object : PagedList.BoundaryCallback<ShowModelMinimal>() {
        override fun onItemAtEndLoaded(itemAtEnd: ShowModelMinimal) {
            updateCache()
        }
    }

    override fun onCleared() {
        repository.cancelAllJobs()
    }
}