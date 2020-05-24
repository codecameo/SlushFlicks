package com.sifat.slushflicks.ui.home.adapter.model

import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.base.ListModel
import com.sifat.slushflicks.ui.base.ListViewState

class CollectionListModel(data: CollectionModel? = null, state: ListViewState) :
    ListModel<CollectionModel>(data, state) {
    override fun getViewType() = COLLECTION_VIEW_TYPE

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CollectionListModel
        return other.getViewType() == getViewType() && other.data == data && state == other.state
    }

    companion object {
        const val COLLECTION_VIEW_TYPE = 1002
    }
}