package com.sifat.slushflicks.ui.home.adapter.model

import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.base.ListModel
import com.sifat.slushflicks.ui.base.ListViewState

class MovieListModel(data: MovieModelMinimal? = null, state: ListViewState) :
    ListModel<MovieModelMinimal>(data, state) {
    override fun getViewType() = MOVIE_LIST_VIEW_TYPE

    companion object {
        const val MOVIE_LIST_VIEW_TYPE = 1001
    }
}