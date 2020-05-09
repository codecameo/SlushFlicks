package com.sifat.slushflicks.ui.home.search.viewmodel

import androidx.paging.PagedList
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.search.SearchRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.home.search.state.dataaction.SearchDataAction
import com.sifat.slushflicks.ui.home.search.state.dataaction.SearchDataAction.SearchMovieDataAction
import com.sifat.slushflicks.ui.home.search.state.event.SearchEventState
import com.sifat.slushflicks.ui.home.search.state.event.SearchEventState.SetSearchQueryEvent
import com.sifat.slushflicks.ui.home.search.state.event.SearchEventState.UpdateShowTypeEvent
import com.sifat.slushflicks.ui.home.search.state.viewaction.SearchViewAction
import com.sifat.slushflicks.ui.home.search.state.viewaction.SearchViewAction.*
import com.sifat.slushflicks.ui.home.search.state.viewstate.SearchViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.ShowType
import com.sifat.slushflicks.utils.ShowType.MOVIE
import com.sifat.slushflicks.utils.ShowType.TV_SERIES
import javax.inject.Inject

@HomeScope
class SearchViewModel
@Inject constructor(
    private val searchRepository: SearchRepository
) : BaseActionViewModel<SearchDataAction, SearchViewAction, SearchViewState>() {

    fun handleEvent(viewEvent: SearchEventState) {
        when (viewEvent) {
            is SearchEventState.ShowSearchEvent -> {
                searchShow(viewEvent)
            }
            is SetSearchQueryEvent -> {
                seyQueryText(viewEvent)
            }
            is UpdateShowTypeEvent -> {
                updateShowType(viewEvent)
            }
        }
    }

    private fun updateShowType(viewEvent: UpdateShowTypeEvent) {
        if (viewState.showType == viewEvent.showType) return
        viewState.showType = viewEvent.showType
        getAction().value = UpdateShowTypeViewAction(
            ViewState.Success<ShowType>(
                data = viewEvent.showType
            )
        )
    }

    private fun seyQueryText(viewEvent: SetSearchQueryEvent) {
        getAction().value = UpdateQueryViewAction(
            ViewState.Success(viewState.queryModel)
        )
    }

    private fun searchShow(viewEvent: SearchEventState.ShowSearchEvent) {
        getAction().value = SearchShowViewAction(
            ViewState.Loading<PagedList<ShowModelMinimal>>()
        )
        val source = when (viewState.showType) {
            MOVIE -> {
                searchRepository.searchMovies(viewState.queryModel.query, boundaryCallback)
            }
            TV_SERIES -> {
                searchRepository.searchTvShows(viewState.queryModel.query, boundaryCallback)
            }
        }
        dataState.addSource(source) { dataResponse ->
            dataState.removeSource(source)
            dataState.value = SearchMovieDataAction(dataResponse)
        }
    }

    fun setDataAction(action: SearchMovieDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<PagedList<ShowModelMinimal>> -> {
                dataState.dataResponse.data?.let { movieList ->
                    viewState.showList = movieList
                    sendMovieSearchSuccessAction()
                }
            }
        }
    }

    private fun sendMovieSearchSuccessAction() {
        getAction().value = SearchShowViewAction(
            ViewState.Success<PagedList<ShowModelMinimal>>(
                data = viewState.showList
            )
        )
    }

    override val viewState: SearchViewState by lazy {
        SearchViewState()
    }

    private val boundaryCallback = object : PagedList.BoundaryCallback<ShowModelMinimal>() {
        override fun onZeroItemsLoaded() {
            getAction().value = SearchViewAction.ResultFoundViewAction(
                ViewState.Success<Boolean>(
                    data = false
                )
            )
        }

        override fun onItemAtFrontLoaded(itemAtFront: ShowModelMinimal) {
            getAction().value = SearchViewAction.ResultFoundViewAction(
                ViewState.Success<Boolean>(
                    data = true
                )
            )
        }
    }

    companion object {
        private const val TAG = "SearchViewModel"
    }
}