package com.sifat.slushflicks.ui.home.movie.state.viewstate

import com.sifat.slushflicks.ui.base.BaseViewState
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.home.movie.state.MovieListViewAction

class MovieListViewState : BaseViewState<MovieListViewAction>() {
    var currentPage = 0
    var movieList: List<MovieListModel>? = null
    fun nextPage() = currentPage + 1
}