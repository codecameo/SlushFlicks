package com.sifat.slushflicks.ui.details.viewmodel

import androidx.lifecycle.Observer
import com.sifat.slushflicks.di.details.DetailsScope
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.repository.MovieDetailsRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction.*
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent.*
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction.*
import com.sifat.slushflicks.ui.details.state.viewstate.DetailsViewState
import com.sifat.slushflicks.ui.helper.getMovieListModel
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState
import javax.inject.Inject

@DetailsScope
class DetailsViewModel
@Inject constructor(private val detailsRepository: MovieDetailsRepository) :
    BaseActionViewModel<DetailDataAction, DetailsViewAction, DetailsViewState>() {
    override val viewState by lazy {
        DetailsViewState()
    }

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
            is FetchRecommendedMovieViewEvent -> {
                fetchRecommendedMovies(viewState.movieId)
            }
            is FetchSimilarMovieViewEvent -> {
                fetchSimilarMovies(viewState.movieId)
            }
        }
    }

    /*********** Fetch data from repo ************/

    private fun fetchSimilarMovies(movieId: Long) {
        val similarSource = detailsRepository.getSimilarMovies(movieId)
        dataState.addSource(similarSource) { similarMovies ->
            dataState.removeSource(similarSource)
            dataState.value = FetchSimilarDataAction(
                dataState = similarMovies
            )
        }
    }

    private fun fetchRecommendedMovies(movieId: Long) {
        val recommendedSource = detailsRepository.getRecommendationMovies(movieId)
        dataState.addSource(recommendedSource) { recommendedMovies ->
            dataState.removeSource(recommendedSource)
            dataState.value = FetchRecommendationDataAction(
                dataState = recommendedMovies
            )
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

    /*********** Send action to view ************/

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

    fun setDataAction(action: FetchSimilarDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<List<MovieModelMinimal>> -> {
                dataState.dataResponse.data?.let { movie ->
                    viewState.similarMovies = getMovieListModel(movie)
                    sendSimilarSuccessAction(dataState)
                }
            }
            is DataState.Error<List<MovieModelMinimal>> -> {
                sendSimilarErrorAction(dataState.dataResponse)
            }
        }
    }

    fun setDataAction(action: FetchRecommendationDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<List<MovieModelMinimal>> -> {
                dataState.dataResponse.data?.let { movie ->
                    viewState.recommendedMovies = getMovieListModel(movie)
                    sendRecommendationSuccessAction(dataState)
                }
            }
            is DataState.Error<List<MovieModelMinimal>> -> {
                sendRecommendationErrorAction(dataState.dataResponse)
            }
        }
    }

    /*********** Send success action to view ************/

    private fun sendRecommendationSuccessAction(dataState: DataState.Success<List<MovieModelMinimal>>) {
        getAction().value = FetchRecommendationViewAction(
            ViewState.Success(
                data = viewState.recommendedMovies,
                message = dataState.dataResponse.message
            )
        )
    }

    private fun sendSimilarSuccessAction(dataState: DataState.Success<List<MovieModelMinimal>>) {
        getAction().value = FetchSimilarViewAction(
            ViewState.Success(
                data = viewState.similarMovies,
                message = dataState.dataResponse.message
            )
        )
    }

    private fun sendMovieSuccessAction() {
        getAction().value = FetchDetailsViewAction(
            ViewState.Success(viewState.movie)
        )
    }

    /*********** Send error action to view ************/

    private fun sendRecommendationErrorAction(dataResponse: DataErrorResponse<List<MovieModelMinimal>>) {
        getAction().value = FetchRecommendationViewAction(
            ViewState.Error(
                errorMessage = dataResponse.errorMessage
            )
        )
    }

    private fun sendSimilarErrorAction(dataResponse: DataErrorResponse<List<MovieModelMinimal>>) {
        getAction().value = FetchSimilarViewAction(
            ViewState.Error(
                errorMessage = dataResponse.errorMessage
            )
        )
    }
}