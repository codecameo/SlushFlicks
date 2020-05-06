package com.sifat.slushflicks.ui.details.viewmodel

import androidx.lifecycle.Observer
import com.sifat.slushflicks.di.details.TvDetailsScope
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.tv.TvDetailsRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.details.state.dataaction.TvDetailDataAction
import com.sifat.slushflicks.ui.details.state.dataaction.TvDetailDataAction.FetchTvCastDataAction
import com.sifat.slushflicks.ui.details.state.dataaction.TvDetailDataAction.FetchTvDetailsDataAction
import com.sifat.slushflicks.ui.details.state.event.TvDetailsViewEvent
import com.sifat.slushflicks.ui.details.state.event.TvDetailsViewEvent.*
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
            is FetchTvDetailsViewEvent -> {
                fetchTvShowDetails(viewState.tvShowId)
            }
            is FetchTvVideoViewEvent -> {
                fetchTvShowVideo(viewState.tvShowId, viewState.tvModel.numOfSeason)
            }
            is FetchTvCastViewEvent -> {
                fetchTvCast(viewState.tvShowId)
            }
            is FetchRecommendedTvViewEvent -> {
                //fetchRecommendedMovies(viewState.tvShowId)
            }
            is FetchSimilarTvViewEvent -> {
                //fetchSimilarMovies(viewState.tvShowId)
            }
            is FetchTvReviewsViewEvent -> {
                //fetchMovieReviews(viewState.tvShowId)
            }
            is UpdateTvViewEvent -> {
                //updateMovieInfo(movieDetailsViewEvent.showModelMinimal)
            }
        }
    }

    private fun fetchTvShowVideo(tvShowId: Long, seasonNumber: Int) {
        if (viewState.isAlreadyVideoAttempted || seasonNumber == 0) return
        viewState.isAlreadyVideoAttempted = true
        val videoSource = detailsRepository.getTvShowVideo(tvShowId, seasonNumber)
        dataState.addSource(videoSource) { videoKey ->
            dataState.removeSource(videoSource)
            dataState.value = TvDetailDataAction.FetchTvVideoDataAction(
                dataState = videoKey
            )
        }
    }

    private fun fetchTvCast(tvShowId: Long) {
        if (viewState.isAlreadyCastAttempted) return
        viewState.isAlreadyCastAttempted = true
        val castSource = detailsRepository.getTvShowCast(tvShowId)
        dataState.addSource(castSource) { castCount ->
            dataState.removeSource(castSource)
            dataState.value = FetchTvCastDataAction(
                dataState = castCount
            )
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