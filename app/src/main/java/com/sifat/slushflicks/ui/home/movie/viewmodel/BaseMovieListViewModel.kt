package com.sifat.slushflicks.ui.home.movie.viewmodel

import androidx.paging.PagedList
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.repository.BaseMovieListRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction.FetchCacheMovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.dataaction.MovieListDataAction.FetchNetworkMovieListDataAction
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState
import com.sifat.slushflicks.ui.home.movie.state.event.MovieListEventState.FetchMovieListEvent
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieListViewAction.FetchCacheMovieListViewAction
import com.sifat.slushflicks.ui.home.movie.state.viewstate.MovieListViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.ViewState
import javax.inject.Inject

open class BaseMovieListViewModel
@Inject constructor(private val repository: BaseMovieListRepository) :
    BaseActionViewModel<MovieListDataAction, MovieListViewAction, MovieListViewState>() {
    override val viewState by lazy {
        MovieListViewState()
    }

    private val TAG = "BaseMovieListViewModel"

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
        val initialUpdate = repository.getMovieList(viewState.nextPage())
        dataState.addSource(initialUpdate) { dataResponse ->
            dataState.removeSource(initialUpdate)
            dataState.value = FetchNetworkMovieListDataAction(dataResponse)
        }
        fetchFromCache()
    }

    private fun fetchFromCache() {
        dataState.addSource(repository.getPagingMovieList(boundaryCallback)) { dataResponse ->
            dataState.value = FetchCacheMovieListDataAction(dataResponse)
        }
    }

    private fun updateCache() {
        if (viewState.currentPage < viewState.totalPage) {
            val networkUpdate = repository.getMovieList(viewState.nextPage())
            dataState.addSource(networkUpdate) { dataResponse ->
                dataState.removeSource(networkUpdate)
                dataState.value = FetchNetworkMovieListDataAction(dataResponse)
            }
        }
    }

    fun setDataAction(actionCache: FetchCacheMovieListDataAction) {
        when (val dataState = actionCache.dataState) {
            is DataState.Success<PagedList<MovieModelMinimal>> -> {
                dataState.dataResponse.data?.let { movie ->
                    viewState.movieList = movie
                    sendMovieListSuccessAction()
                }
            }
            is Error<PagedList<MovieModelMinimal>> -> {
                sendMovieListErrorAction(dataState)
            }
        }
    }

    fun setDataAction(actionNetwork: FetchNetworkMovieListDataAction) {
        when (val dataState = actionNetwork.dataState) {
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

    private fun sendMovieListErrorAction(dataState: Error<PagedList<MovieModelMinimal>>) {
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
                viewState.movieList
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

    private val boundaryCallback = object : PagedList.BoundaryCallback<MovieModelMinimal>() {
        override fun onItemAtEndLoaded(itemAtEnd: MovieModelMinimal) {
            updateCache()
        }

        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
        }
    }
}