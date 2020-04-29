package com.example.slushflicks.ui.home.adapter.model

import com.example.slushflicks.model.MovieModelMinimal
import com.example.slushflicks.ui.base.ListModel
import com.example.slushflicks.ui.base.ListViewState

class MovieListModel(data: MovieModelMinimal? = null, state: ListViewState) :
    ListModel<MovieModelMinimal>(data, state) {
    override fun getViewType() = MOVIE_LIST_VIEW_TYPE

    companion object {
        const val MOVIE_LIST_VIEW_TYPE = 1001
    }
}