package com.sifat.slushflicks.ui.details.viewmodel

import androidx.lifecycle.Observer
import com.sifat.slushflicks.di.details.DetailsScope
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.repository.MovieDetailsRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction.*
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent.*
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction.FetchDetailsViewAction
import com.sifat.slushflicks.ui.details.state.viewstate.DetailsViewState
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState
import javax.inject.Inject

@DetailsScope
class DetailsViewModel
@Inject constructor(private val detailsRepository: MovieDetailsRepository) :
    BaseActionViewModel<DetailDataAction, DetailsViewAction, DetailsViewState>() {
    fun setMovieId(movieId: Long) {
        viewState.movieId = movieId
    }

    fun handleEvent(detailsViewEvent: DetailsViewEvent) {
        when (detailsViewEvent) {
            is FetchDetailsViewEvent -> {
                fetchMovieDetails(viewState.movieId)
            }
            is FetchVideoViewEvent -> {
                fetchMovieVideo(viewState.movieId)
            }
            is FetchCastViewEvent -> {
                fetchMovieCast(viewState.movieId)
            }
        }
    }

    private fun fetchMovieVideo(movieId: Long) {
        val videoSource = detailsRepository.getMovieVideo(movieId)
        dataState.addSource(videoSource) { videoKey ->
            dataState.removeSource(videoSource)
            dataState.value = FetchVideoDataAction(
                dataState = videoKey
            )
        }
    }

    private fun fetchMovieCast(movieId: Long) {
        val castSource = detailsRepository.getMovieCast(movieId)
        dataState.addSource(castSource) { castCount ->
            dataState.removeSource(castSource)
            dataState.value = FetchCastDataAction(
                dataState = castCount
            )
        }
    }

    private fun fetchMovieDetails(movieId: Long) {
        dataState.addSource(
            detailsRepository.getMovieDetails(movieId = movieId),
            Observer { dataResponse ->
                dataState.value = FetchMovieDetailsAction(dataResponse)
            })
    }

    fun setDataAction(action: FetchMovieDetailsAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<MovieModel> -> {
                dataState.dataResponse.data?.let { movie ->
                    viewState.movie = movie
                    sendMovieSuccessAction()
                }
            }
        }
    }

    private fun sendMovieSuccessAction() {
        getAction().value = FetchDetailsViewAction(
            ViewState.Success(viewState.movie)
        )
    }

    override val viewState by lazy {
        DetailsViewState()
    }
}