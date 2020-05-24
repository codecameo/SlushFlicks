package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.utils.LOADING_MODEL_COUNT

fun getShowListLoadingModels(): List<ShowListModel> {
    val list = mutableListOf<ShowListModel>()
    for (count in 0..LOADING_MODEL_COUNT) {
        list.add(ShowListModel(null, ListViewState.LOADING))
    }
    return list
}