package com.sifat.slushflicks.ui.details.state.viewaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.state.ViewState

sealed class DetailsViewAction {
    data class FetchDetailsViewAction(val viewState: ViewState<MovieModel>) : DetailsViewAction()
    data class FetchRecommendationViewAction(val viewState: ViewState<List<MovieListModel>>) :
        DetailsViewAction()
    data class FetchSimilarViewAction(val viewState: ViewState<List<MovieListModel>>) :
        DetailsViewAction()

    data class FetchReviewViewAction(val viewState: ViewState<PagedList<ReviewModel>>) :
        DetailsViewAction()
}