package com.sifat.slushflicks.ui.details.state.dataaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.ui.state.DataState

sealed class TvDetailDataAction {
    class FetchTvDetailsDataAction(val dataState: DataState<TvModel>) : TvDetailDataAction()
    class FetchTvVideoDataAction(val dataState: DataState<String>) : TvDetailDataAction()
    class FetchTvCastDataAction(val dataState: DataState<Int>) : TvDetailDataAction()
    class FetchTvRecommendationDataAction(val dataState: DataState<List<ShowModelMinimal>>) :
        TvDetailDataAction()

    class FetchTvSimilarDataAction(val dataState: DataState<List<ShowModelMinimal>>) :
        TvDetailDataAction()

    class FetchTvReviewDataAction(val dataState: DataState<PagedList<ReviewModel>>) :
        TvDetailDataAction()
}