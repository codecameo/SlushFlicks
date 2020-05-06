package com.sifat.slushflicks.ui.details.state.viewaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.ui.state.ViewState

sealed class MovieDetailsViewAction {
    data class FetchMovieDetailsViewAction(val viewState: ViewState<MovieModel>) :
        MovieDetailsViewAction()

    data class FetchRecommendedMovieViewAction(val viewState: ViewState<List<ShowListModel>>) :
        MovieDetailsViewAction()

    data class FetchSimilarMovieViewAction(val viewState: ViewState<List<ShowListModel>>) :
        MovieDetailsViewAction()

    data class FetchMovieReviewViewAction(val viewState: ViewState<PagedList<ReviewModel>>) :
        MovieDetailsViewAction()
}