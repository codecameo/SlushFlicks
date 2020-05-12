package com.sifat.slushflicks.ui.details.viewmodel

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.sifat.slushflicks.di.details.MovieDetailsScope
import com.sifat.slushflicks.helper.DynamicLinkProvider
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.movie.MovieDetailsRepository
import com.sifat.slushflicks.ui.base.BaseActionViewModel
import com.sifat.slushflicks.ui.details.state.dataaction.MovieDetailDataAction
import com.sifat.slushflicks.ui.details.state.dataaction.MovieDetailDataAction.*
import com.sifat.slushflicks.ui.details.state.event.MovieDetailsViewEvent
import com.sifat.slushflicks.ui.details.state.event.MovieDetailsViewEvent.*
import com.sifat.slushflicks.ui.details.state.viewaction.MovieDetailsViewAction
import com.sifat.slushflicks.ui.details.state.viewaction.MovieDetailsViewAction.*
import com.sifat.slushflicks.ui.details.state.viewstate.MovieDetailsViewState
import com.sifat.slushflicks.ui.helper.getMovieListModel
import com.sifat.slushflicks.ui.helper.getShowListLoadingModels
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.DynamicLinkConst
import java.lang.ref.WeakReference
import javax.inject.Inject

@MovieDetailsScope
class MovieDetailsViewModel
@Inject constructor(
    private val detailsRepository: MovieDetailsRepository,
    private val dynamicLinkProvider: DynamicLinkProvider
) : BaseActionViewModel<MovieDetailDataAction, MovieDetailsViewAction, MovieDetailsViewState>(),
    DynamicLinkProvider.OnEventShareCallback {
    override val viewState by lazy {
        MovieDetailsViewState()
    }

    fun setMovieId(movieId: Long) {
        viewState.movieId = movieId
    }

    fun handleEvent(movieDetailsViewEvent: MovieDetailsViewEvent) {
        when (movieDetailsViewEvent) {
            is FetchMovieDetailsViewEvent -> {
                fetchMovieDetails(viewState.movieId)
            }
            is FetchMovieVideoViewEvent -> {
                fetchMovieVideo(viewState.movieId)
            }
            is FetchMovieCastViewEvent -> {
                fetchMovieCast(viewState.movieId)
            }
            is FetchRecommendedMovieViewEvent -> {
                fetchRecommendedMovies(viewState.movieId)
            }
            is FetchSimilarMovieViewEvent -> {
                fetchSimilarMovies(viewState.movieId)
            }
            is FetchMovieReviewsViewEvent -> {
                fetchMovieReviews(viewState.movieId)
            }
            is UpdateMovieViewEvent -> {
                updateMovieInfo(movieDetailsViewEvent.showModelMinimal)
            }
            is ShareMovieViewEvent -> {
                shareMovie()
            }
        }
    }

    private fun shareMovie() {
        getAction().value = ShareMovieViewAction(ViewState.Loading())
        val dynamicLinkParam = DynamicLinkProvider.DynamicLinkParam(
            showId = viewState.movieId,
            showName = viewState.movie.title,
            showType = DynamicLinkConst.MOVIE_SHOW_TYPE,
            overview = viewState.movie.overview,
            imageUrl = viewState.movie.backdropPath
        )
        val callback = WeakReference<DynamicLinkProvider.OnEventShareCallback>(this)
        dynamicLinkProvider.generateDynamicLink(dynamicLinkParam, callback)
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
        getAction().value = FetchSimilarMovieViewAction(
            ViewState.Loading<List<ShowListModel>>(getShowListLoadingModels())
        )
        val similarSource = detailsRepository.getSimilarMovies(movieId)
        dataState.addSource(similarSource) { similarMovies ->
            dataState.removeSource(similarSource)
            dataState.value = FetchMovieSimilarDataAction(
                dataState = similarMovies
            )
        }
    }

    private fun fetchRecommendedMovies(movieId: Long) {
        getAction().value = FetchRecommendedMovieViewAction(
            ViewState.Loading<List<ShowListModel>>(getShowListLoadingModels())
        )
        val recommendedSource = detailsRepository.getRecommendationMovies(movieId)
        dataState.addSource(recommendedSource) { recommendedMovies ->
            dataState.removeSource(recommendedSource)
            dataState.value = FetchMovieRecommendationDataAction(
                dataState = recommendedMovies
            )
        }
    }

    private fun fetchMovieVideo(movieId: Long) {
        // Checking if the tv model is being fetched from database.
        // if not video key won't be saved in the database since it performs update operation
        if (viewState.isAlreadyVideoAttempted && viewState.movie.voteCount == 0) return
        viewState.isAlreadyVideoAttempted = true
        val videoSource = detailsRepository.getMovieVideo(movieId)
        dataState.addSource(videoSource) { videoKey ->
            dataState.removeSource(videoSource)
            dataState.value = FetchMovieVideoDataAction(
                dataState = videoKey
            )
        }
    }

    private fun fetchMovieCast(movieId: Long) {
        // Checking if the tv model is being fetched from database.
        // if not casting data won't be saved in the database since it performs update operation
        if (viewState.isAlreadyCastAttempted && viewState.movie.voteCount == 0) return
        viewState.isAlreadyCastAttempted = true
        val castSource = detailsRepository.getMovieCast(movieId)
        dataState.addSource(castSource) { castCount ->
            dataState.removeSource(castSource)
            dataState.value = FetchMovieCastDataAction(
                dataState = castCount
            )
        }
    }

    private fun fetchMovieDetails(movieId: Long) {
        dataState.addSource(
            detailsRepository.getMovieDetails(movieId = movieId),
            Observer { dataResponse ->
                dataState.value = FetchMovieDetailsDataAction(dataResponse)
            })
    }

    private fun fetchMovieReviews(movieId: Long) {
        dataState.addSource(detailsRepository.getReviews(movieId),
            Observer { reviewList ->
                dataState.value = FetchMovieReviewDataAction(reviewList)
            })
    }

    private fun updateRecentMovie(movie: MovieModel) {
        detailsRepository.updateRecentMovie(movie.id)
    }

    /*********** Send action to view ************/

    fun setDataAction(action: FetchMovieDetailsDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<MovieModel> -> {
                dataState.dataResponse.data?.let { movie ->
                    viewState.movie = movie
                    sendMovieSuccessAction()
                    updateRecentMovie(movie)
                }
            }
        }
    }

    fun setDataAction(action: FetchMovieSimilarDataAction) {
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

    fun setDataAction(action: FetchMovieRecommendationDataAction) {
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

    fun setDataAction(action: FetchMovieReviewDataAction) {
        when (val dataState = action.dataState) {
            is DataState.Success<PagedList<ReviewModel>> -> {
                viewState.reviews = dataState.dataResponse.data
                sendReviewSuccessAction()
            }
        }
    }

    /*********** Send success action to view ************/

    private fun sendRecommendationSuccessAction(dataState: DataState.Success<List<ShowModelMinimal>>) {
        getAction().value = FetchRecommendedMovieViewAction(
            ViewState.Success(
                data = viewState.recommendedMovies,
                message = dataState.dataResponse.message
            )
        )
    }

    private fun sendSimilarSuccessAction(dataState: DataState.Success<List<ShowModelMinimal>>) {
        getAction().value = FetchSimilarMovieViewAction(
            ViewState.Success(
                data = viewState.similarMovies,
                message = dataState.dataResponse.message
            )
        )
    }

    private fun sendMovieSuccessAction() {
        getAction().value = FetchMovieDetailsViewAction(
            ViewState.Success(viewState.movie)
        )
    }

    private fun sendReviewSuccessAction() {
        getAction().value = FetchMovieReviewViewAction(
            ViewState.Success(viewState.reviews)
        )
    }

    override fun onSuccess(shortUrl: String?) {
        getAction().value = ShareMovieViewAction(
            ViewState.Success(
                data = shortUrl
            )
        )
    }

    /*********** Send error action to view ************/

    private fun sendRecommendationErrorAction(dataResponse: DataErrorResponse<List<ShowModelMinimal>>) {
        getAction().value = FetchRecommendedMovieViewAction(
            ViewState.Error(
                errorMessage = dataResponse.errorMessage
            )
        )
    }

    private fun sendSimilarErrorAction(dataResponse: DataErrorResponse<List<ShowModelMinimal>>) {
        getAction().value = FetchSimilarMovieViewAction(
            ViewState.Error(
                errorMessage = dataResponse.errorMessage
            )
        )
    }

    override fun onFailure() {
        getAction().value = ShareMovieViewAction(
            ViewState.Error()
        )
    }

    override fun onCleared() {
        detailsRepository.cancelAllJobs()
    }
}