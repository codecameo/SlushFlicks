package com.sifat.slushflicks.data

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataState

interface FireStoreManager {

    fun getMovieCollections(): LiveData<DataState<List<CollectionModel>>>
}