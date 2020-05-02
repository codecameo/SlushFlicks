package com.sifat.slushflicks.ui.details.state.dataaction

import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.state.DataState

sealed class DetailDataAction {
    class FetchMovieDetailsAction(val dataState: DataState<MovieModel>) : DetailDataAction()
    class FetchVideoDataAction(val dataState: DataState<String>) : DetailDataAction()
    class FetchCastDataAction(val dataState: DataState<Int>) : DetailDataAction()
    class FetchRecommendationDataAction(val dataState: DataState<List<MovieModelMinimal>>) :
        DetailDataAction()

    class FetchSimilarDataAction(val dataState: DataState<List<MovieModelMinimal>>) :
        DetailDataAction()
}