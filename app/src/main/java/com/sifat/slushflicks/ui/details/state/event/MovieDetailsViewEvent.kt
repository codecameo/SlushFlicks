package com.sifat.slushflicks.ui.details.state.event

import com.sifat.slushflicks.model.ShowModelMinimal

sealed class MovieDetailsViewEvent {
    data class FetchMovieDetailsViewEvent(val forceUpdate: Boolean = true) : MovieDetailsViewEvent()
    class FetchMovieVideoViewEvent : MovieDetailsViewEvent()
    class FetchMovieCastViewEvent : MovieDetailsViewEvent()
    class FetchRecommendedMovieViewEvent : MovieDetailsViewEvent()
    class FetchSimilarMovieViewEvent : MovieDetailsViewEvent()
    class FetchMovieReviewsViewEvent : MovieDetailsViewEvent()
    class UpdateMovieViewEvent(val showModelMinimal: ShowModelMinimal) : MovieDetailsViewEvent()
    class ShareMovieViewEvent : MovieDetailsViewEvent()
}