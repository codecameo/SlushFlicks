package com.sifat.slushflicks.ui.home.tvshow.viewmodel

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.tv.TvListRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.home.tvshow.state.dataaction.TvListDataAction
import com.sifat.slushflicks.ui.home.tvshow.state.event.TvListEventState
import com.sifat.slushflicks.ui.home.tvshow.state.viewaction.TvListViewAction
import com.sifat.slushflicks.ui.home.tvshow.state.viewstate.TvListViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState

open class BaseTvListViewModel(private val repository: TvListRepository) :
    BaseActionViewModel<TvListDataAction, TvListViewAction, TvListViewState>() {
    override val viewState by lazy {
        TvListViewState()
    }

    fun handleEvent(event: TvListEventState) {
        when (event) {
            is TvListEventState.FetchTvListEvent -> {
                fetchTvShow(event)
            }
        }
    }

    private fun fetchTvShow(event: TvListEventState.FetchTvListEvent) {
        if (!event.forceUpdate) {
            if (!viewState.showList.isNullOrEmpty()) {
                sendTvListSuccessAction()
                return
            }
        }
        sendTvListLoadingAction()
        val initialUpdate = repository.getTvList(viewState.nextPage())
        dataState.addSource(initialUpdate) { dataResponse ->
            dataState.removeSource(initialUpdate)
            dataState.value = TvListDataAction.FetchNetworkTvListDataAction(dataResponse)
        }
        fetchFromCache()
    }

    private fun fetchFromCache() {
        dataState.addSource(repository.getPagingTvList(boundaryCallback)) { dataResponse ->
            dataState.value = TvListDataAction.FetchCacheTvListDataAction(dataResponse)
        }
    }


    private fun updateCache() {
        if (viewState.currentPage < viewState.totalPage) {
            val networkUpdate = repository.getTvList(viewState.nextPage())
            dataState.addSource(networkUpdate) { dataResponse ->
                dataState.removeSource(networkUpdate)
                dataState.value = TvListDataAction.FetchNetworkTvListDataAction(dataResponse)
            }
        }
    }

    fun setDataAction(actionCache: TvListDataAction.FetchCacheTvListDataAction) {
        when (val dataState = actionCache.dataState) {
            is DataState.Success<PagedList<ShowModelMinimal>> -> {
                dataState.dataResponse.data?.let { movie ->
                    viewState.showList = movie
                    sendTvListSuccessAction()
                }
            }
            is DataState.Error<PagedList<ShowModelMinimal>> -> {
                sendTvListErrorAction(dataState)
            }
        }
    }

    fun setDataAction(actionNetwork: TvListDataAction.FetchNetworkTvListDataAction) {
        when (val dataState = actionNetwork.dataState) {
            is DataState.Success<Int> -> {
                dataState.dataResponse.metaData?.run {
                    viewState.totalPage = totalPage
                    viewState.currentPage = page
                }
            }
            is DataState.Error<Int> -> {
                sendTvListUpdateErrorAction(dataState)
            }
        }
    }

    /**
     * Send loading action to the view
     * */
    private fun sendTvListLoadingAction() {
        getAction().value = TvListViewAction.FetchCacheTvListViewAction(
            ViewState.Loading(
                //getMovieListLoadingModels()
            )
        )
    }

    /**
     * Send movie list to the view
     * */
    private fun sendTvListSuccessAction() {
        getAction().value = TvListViewAction.FetchCacheTvListViewAction(
            ViewState.Success(
                viewState.showList
            )
        )
    }

    private fun sendTvListErrorAction(dataState: DataState.Error<PagedList<ShowModelMinimal>>) {
        getAction().value = TvListViewAction.FetchCacheTvListViewAction(
            ViewState.Error(
                errorMessage = dataState.dataResponse.errorMessage
            )
        )
    }

    private fun sendTvListUpdateErrorAction(dataState: DataState.Error<Int>) {
        getAction().value = TvListViewAction.FetchNetworkTvListViewAction(
            ViewState.Error(
                errorMessage = dataState.dataResponse.errorMessage
            )
        )
    }


    private val boundaryCallback = object : PagedList.BoundaryCallback<ShowModelMinimal>() {
        override fun onItemAtEndLoaded(itemAtEnd: ShowModelMinimal) {
            updateCache()
        }

        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
        }
    }

    override fun onCleared() {
        repository.cancelAllJobs()
    }
}