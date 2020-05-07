package com.sifat.slushflicks.ui.details.state.viewaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.ui.state.ViewState

sealed class TvDetailsViewAction {
    data class FetchTvDetailsViewAction(val viewState: ViewState<TvModel>) : TvDetailsViewAction()
    data class FetchRecommendedTvViewAction(val viewState: ViewState<List<ShowListModel>>) :
        TvDetailsViewAction()

    data class FetchSimilarTvViewAction(val viewState: ViewState<List<ShowListModel>>) :
        TvDetailsViewAction()

    data class FetchTvReviewViewAction(val viewState: ViewState<PagedList<ReviewModel>>) :
        TvDetailsViewAction()

    class ShareTvSeriesViewAction(val viewState: ViewState<String>) : TvDetailsViewAction()
}