package com.sifat.slushflicks.ui.home.adapter.model

import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.base.ListModel
import com.sifat.slushflicks.ui.base.ListViewState

class CollectionListModel(data: CollectionModel? = null, state: ListViewState) :
    ListModel<CollectionModel>(data, state) {
    override fun getViewType() = COLLECTION_VIEW_TYPE

    companion object {
        const val COLLECTION_VIEW_TYPE = 1002
    }
}