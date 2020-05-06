package com.sifat.slushflicks.ui.details.state.event

import com.sifat.slushflicks.model.ShowModelMinimal

sealed class TvDetailsViewEvent {
    data class FetchTvDetailsViewEvent(val forceUpdate: Boolean = true) : TvDetailsViewEvent()
    class FetchTvVideoViewEvent : TvDetailsViewEvent()
    class FetchTvCastViewEvent : TvDetailsViewEvent()
    class FetchRecommendedTvViewEvent : TvDetailsViewEvent()
    class FetchSimilarTvViewEvent : TvDetailsViewEvent()
    class FetchTvReviewsViewEvent : TvDetailsViewEvent()
    class UpdateTvViewEvent(val showModelMinimal: ShowModelMinimal) : TvDetailsViewEvent()
}