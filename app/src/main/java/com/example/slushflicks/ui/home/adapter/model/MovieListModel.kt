package com.example.slushflicks.ui.home.adapter.model

import com.example.slushflicks.ui.base.ListModel
import com.example.slushflicks.ui.base.ListViewState
import com.example.slushflicks.ui.model.MovieModel

class MovieListModel(data : MovieModel? = null, state: ListViewState) : ListModel<MovieModel>(data, state) {
    override fun getViewType() = MOVIE_LIST_VIEW_TYPE
    companion object {
        const val MOVIE_LIST_VIEW_TYPE = 1001
    }
}