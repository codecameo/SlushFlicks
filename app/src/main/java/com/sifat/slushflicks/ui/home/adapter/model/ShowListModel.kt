package com.sifat.slushflicks.ui.home.adapter.model

import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.ListModel
import com.sifat.slushflicks.ui.base.ListViewState

class ShowListModel(data: ShowModelMinimal? = null, state: ListViewState) :
    ListModel<ShowModelMinimal>(data, state) {
    override fun getViewType() = MOVIE_LIST_VIEW_TYPE

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ShowListModel
        return getViewType() == other.getViewType() && data == other.data && state == other.state
    }

    companion object {
        const val MOVIE_LIST_VIEW_TYPE = 1001
    }
}