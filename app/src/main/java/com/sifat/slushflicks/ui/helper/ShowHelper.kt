package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel


fun getShowListLoadingModels(): List<ShowListModel> {
    val list = mutableListOf<ShowListModel>()
    for (count in 0..5) {
        list.add(ShowListModel(null, ListViewState.LOADING))
    }
    return list
}