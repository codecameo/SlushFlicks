package com.sifat.slushflicks.ui.details.state.event

import com.sifat.slushflicks.model.ShowModelMinimal

sealed class DetailsViewEvent {
    data class FetchDetailsViewEvent(val forceUpdate: Boolean = true) : DetailsViewEvent()
    class FetchVideoViewEvent : DetailsViewEvent()
    class FetchCastViewEvent : DetailsViewEvent()
    class FetchRecommendedMovieViewEvent : DetailsViewEvent()
    class FetchSimilarMovieViewEvent : DetailsViewEvent()
    class FetchReviewsViewEvent : DetailsViewEvent()
    class UpdateMovieViewEvent(val showModelMinimal: ShowModelMinimal) : DetailsViewEvent()
}