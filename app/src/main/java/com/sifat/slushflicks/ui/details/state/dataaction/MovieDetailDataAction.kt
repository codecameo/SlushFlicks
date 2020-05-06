package com.sifat.slushflicks.ui.details.state.dataaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

sealed class MovieDetailDataAction {
    class FetchMovieDetailsDataAction(val dataState: DataState<MovieModel>) :
        MovieDetailDataAction()

    class FetchMovieVideoDataAction(val dataState: DataState<String>) : MovieDetailDataAction()
    class FetchMovieCastDataAction(val dataState: DataState<Int>) : MovieDetailDataAction()
    class FetchMovieRecommendationDataAction(val dataState: DataState<List<ShowModelMinimal>>) :
        MovieDetailDataAction()

    class FetchMovieSimilarDataAction(val dataState: DataState<List<ShowModelMinimal>>) :
        MovieDetailDataAction()

    class FetchMovieReviewDataAction(val dataState: DataState<PagedList<ReviewModel>>) :
        MovieDetailDataAction()
}