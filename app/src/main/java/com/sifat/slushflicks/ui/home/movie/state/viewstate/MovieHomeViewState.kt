package com.sifat.slushflicks.ui.home.movie.state.viewstate

import com.sifat.slushflicks.ui.base.BaseViewState
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel
import com.sifat.slushflicks.ui.home.movie.state.viewaction.MovieHomeViewAction

class MovieHomeViewState : BaseViewState<MovieHomeViewAction>() {
    var selectedIndex = 0
    var movieCollectionList: List<CollectionListModel>? = null
        set(value) {
            field = value
            updateListSelection()
        }

    fun updateListSelection() {
        movieCollectionList?.let { list ->
            for (index in list.indices) {
                list[index].data?.run {
                    isEnable = selectedIndex == index
                }
            }
        }
    }

    fun getCurrentLabel(): String? {
        return movieCollectionList?.let { list ->
            list[selectedIndex].data?.label
        }
    }
}