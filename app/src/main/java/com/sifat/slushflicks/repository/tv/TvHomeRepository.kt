package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataState

class TvHomeRepository(private val dataManager: DataManager) {

    fun getTvCollection(): LiveData<DataState<List<CollectionModel>>> {
        return dataManager.getTvCollections()
    }
}