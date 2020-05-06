package com.sifat.slushflicks.ui.details.viewmodel

import androidx.lifecycle.Observer
import com.sifat.slushflicks.di.details.TvDetailsScope
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.tv.TvDetailsRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.details.state.dataaction.TvDetailDataAction
import com.sifat.slushflicks.ui.details.state.dataaction.TvDetailDataAction.FetchTvDetailsDataAction
import com.sifat.slushflicks.ui.details.state.event.TvDetailsViewEvent
import com.sifat.slushflicks.ui.details.state.viewaction.TvDetailsViewAction
import com.sifat.slushflicks.ui.details.state.viewstate.TvDetailsViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState
import javax.inject.Inject

@TvDetailsScope
class TvDetailsViewModel
@Inject constructor(private val detailsRepository: TvDetailsRepository) :
    BaseActionViewModel<TvDetailDataAction, TvDetailsViewAction, TvDetailsViewState>() {

    override val viewState by lazy {
        TvDetailsViewState()
    }

    fun setTvShowId(tvShowId: Long) {
        viewState.tvShowId = tvShowId
    }

    fun handleEvent(tvDetailsViewEvent: TvDetailsViewEvent) {
        when (tvDetailsViewEvent) {
            is TvDetailsViewEvent.FetchTvDetailsViewEvent -> {
                fetchTvShowDetails(viewState.tvShowId)
            }
            is TvDetailsViewEvent.FetchTvVideoViewEvent -> {
                //fetchMovieVideo(viewState.tvShowId)
            }
            is TvDetailsViewEvent.FetchTvCastViewEvent -> {
                //fetchMovieCast(viewState.tvShowId)
            }
            is TvDetailsViewEvent.FetchRecommendedTvViewEvent -> {
                //fetchRecommendedMovies(viewState.tvShowId)
            }
            is TvDetailsViewEvent.FetchSimilarTvViewEvent -> {
                //fetchSimilarMovies(viewState.tvShowId)
            }
            is TvDetailsViewEvent.FetchTvReviewsViewEvent -> {
                //fetchMovieReviews(viewState.tvShowId)
            }
            is TvDetailsViewEvent.UpdateTvViewEvent -> {
                //updateMovieInfo(movieDetailsViewEvent.showModelMinimal)
            }
        }
    }

    private fun fetchTvShowDetails(tvShowId: Long) {
        dataState.addSource(
            detailsRepository.getTvShowDetails(tvShowId = tvShowId),
            Observer { dataResponse ->
                dataState.value = FetchTvDetailsDataAction(dataResponse)
            })
    }

    fun setDataAction(action: FetchTvDetailsDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<TvModel> -> {
                dataState.dataResponse.data?.let { tvShow ->
                    viewState.tvModel = tvShow
                    sendTvSuccessAction()
                }
            }
        }
    }

    private fun sendTvSuccessAction() {
        getAction().value = TvDetailsViewAction.FetchTvDetailsViewAction(
            ViewState.Success(viewState.tvModel)
        )
    }
}