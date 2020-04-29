package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel

fun getCollectionListLoadingModel(): List<CollectionListModel> {
    val list = mutableListOf<CollectionListModel>()
    for (count in 0..5) {
        list.add(CollectionListModel(null, ListViewState.LOADING))
    }
    return list
}

/**
 * This conversion converts model to List models for view state
 * */
fun getCollectionListModel(collections: List<CollectionModel>): List<CollectionListModel> {
    val collectionListModels = mutableListOf<CollectionListModel>()
    for (collection in collections) {
        val collectionListModel = CollectionListModel(collection, ListViewState.VIEW)
        collectionListModels.add(collectionListModel)
    }
    return collectionListModels
}

/**
 * Need to copy the list so that diffutils can calculate the difference
 * */
fun getCopiedCollectionListModel(collections: List<CollectionListModel>): List<CollectionListModel> {
    val collectionListModels = mutableListOf<CollectionListModel>()
    for (collection in collections) {
        val collectionListModel = CollectionListModel(
            collection.data?.copy(
                name = collection.data.name,
                label = collection.data.label,
                isEnable = collection.data.isEnable
            ), collection.state
        )
        collectionListModels.add(collectionListModel)
    }
    return collectionListModels
}