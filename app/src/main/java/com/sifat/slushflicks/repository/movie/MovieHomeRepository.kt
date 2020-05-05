package com.sifat.slushflicks.repository.movie

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataState

class MovieHomeRepository(private val dataManager: DataManager) {

    fun getMovieCollection(): LiveData<DataState<List<CollectionModel>>> {
        return dataManager.getMovieCollections()
    }
}