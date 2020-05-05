package com.sifat.slushflicks.ui.details.viewmodel

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.sifat.slushflicks.di.details.DetailsScope
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.movie.MovieDetailsRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction
import com.sifat.slushflicks.ui.details.state.dataaction.DetailDataAction.*
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent
import com.sifat.slushflicks.ui.details.state.event.DetailsViewEvent.*
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction
import com.sifat.slushflicks.ui.details.state.viewaction.DetailsViewAction.*
import com.sifat.slushflicks.ui.details.state.viewstate.DetailsViewState
import com.sifat.slushflicks.ui.helper.getMovieListLoadingModels
import com.sifat.slushflicks.ui.helper.getMovieListModel
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState
import javax.inject.Inject

@DetailsScope
class MovieDetailsViewModel
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
            is FetchReviewsViewEvent -> {
                fetchMovieReviews(viewState.movieId)
            }
            is UpdateMovieViewEvent -> {
                updateMovieInfo(detailsViewEvent.showModelMinimal)
            }
        }
    }

    private fun updateMovieInfo(showModelMinimal: ShowModelMinimal) {
        setMovieId(showModelMinimal.id)
        val movie = MovieModel(
            id = showModelMinimal.id,
            title = showModelMinimal.title,
            overview = showModelMinimal.overview,
            backdropPath = showModelMinimal.backdropPath,
            voteAvg = showModelMinimal.voteAvg,
            genres = showModelMinimal.genres
        )
        viewState.movie = movie
        sendMovieSuccessAction()
    }

    /*********** Fetch data from repo ************/

    private fun fetchSimilarMovies(movieId: Long) {
        getAction().value = FetchSimilarViewAction(
            ViewState.Loading<List<MovieListModel>>(getMovieListLoadingModels())
        )
        val similarSource = detailsRepository.getSimilarMovies(movieId)
        dataState.addSource(similarSource) { similarMovies ->
            dataState.removeSource(similarSource)
            dataState.value = FetchSimilarDataAction(
                dataState = similarMovies
            )
        }
    }

    private fun fetchRecommendedMovies(movieId: Long) {
        getAction().value = FetchRecommendationViewAction(
            ViewState.Loading<List<MovieListModel>>(getMovieListLoadingModels())
        )
        val recommendedSource = detailsRepository.getRecommendationMovies(movieId)
        dataState.addSource(recommendedSource) { recommendedMovies ->
            dataState.removeSource(recommendedSource)
            dataState.value = FetchRecommendationDataAction(
                dataState = recommendedMovies
            )
        }
    }

    private fun fetchMovieVideo(movieId: Long) {
        if (viewState.isAlreadyVideoAttempted) return
        viewState.isAlreadyVideoAttempted = true
        val videoSource = detailsRepository.getMovieVideo(movieId)
        dataState.addSource(videoSource) { videoKey ->
            dataState.removeSource(videoSource)
            dataState.value = FetchVideoDataAction(
                dataState = videoKey
            )
        }
    }

    private fun fetchMovieCast(movieId: Long) {
        if (viewState.isAlreadyCastAttempted) return
        viewState.isAlreadyCastAttempted = true
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

    private fun fetchMovieReviews(movieId: Long) {
        dataState.addSource(detailsRepository.getReviews(movieId),
            Observer { reviewList ->
                dataState.value = FetchReviewDataAction(reviewList)
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
            is DataState.Success<List<ShowModelMinimal>> -> {
                viewState.similarMovies = getMovieListModel(dataState.dataResponse.data)
                sendSimilarSuccessAction(dataState)
            }
            is DataState.Error<List<ShowModelMinimal>> -> {
                sendSimilarErrorAction(dataState.dataResponse)
            }
        }
    }

    fun setDataAction(action: FetchRecommendationDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<List<ShowModelMinimal>> -> {
                viewState.recommendedMovies = getMovieListModel(dataState.dataResponse.data)
                sendRecommendationSuccessAction(dataState)
            }
            is DataState.Error<List<ShowModelMinimal>> -> {
                sendRecommendationErrorAction(dataState.dataResponse)
            }
        }
    }

    fun setDataAction(action: FetchReviewDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<PagedList<ReviewModel>> -> {
                viewState.reviews = dataState.dataResponse.data
                sendReviewSuccessAction(dataState)
            }
        }
    }

    /*********** Send success action to view ************/

    private fun sendRecommendationSuccessAction(dataState: DataState.Success<List<ShowModelMinimal>>) {
        getAction().value = FetchRecommendationViewAction(
            ViewState.Success(
                data = viewState.recommendedMovies,
                message = dataState.dataResponse.message
            )
        )
    }

    private fun sendSimilarSuccessAction(dataState: DataState.Success<List<ShowModelMinimal>>) {
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

    private fun sendReviewSuccessAction(dataState: DataState.Success<PagedList<ReviewModel>>) {
        getAction().value = FetchReviewViewAction(
            ViewState.Success(viewState.reviews)
        )
    }

    /*********** Send error action to view ************/

    private fun sendRecommendationErrorAction(dataResponse: DataErrorResponse<List<ShowModelMinimal>>) {
        getAction().value = FetchRecommendationViewAction(
            ViewState.Error(
                errorMessage = dataResponse.errorMessage
            )
        )
    }

    private fun sendSimilarErrorAction(dataResponse: DataErrorResponse<List<ShowModelMinimal>>) {
        getAction().value = FetchSimilarViewAction(
            ViewState.Error(
                errorMessage = dataResponse.errorMessage
            )
        )
    }
}