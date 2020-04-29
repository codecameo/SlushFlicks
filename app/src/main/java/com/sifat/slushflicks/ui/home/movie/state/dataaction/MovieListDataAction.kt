package com.sifat.slushflicks.ui.home.movie.state.dataaction

import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.state.DataState

sealed class MovieListDataAction {
    class FetchMovieListDataAction(val dataState: DataState<List<MovieModelMinimal>>) :
        MovieListDataAction()
}