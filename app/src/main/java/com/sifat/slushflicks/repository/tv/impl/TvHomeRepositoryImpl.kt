package com.sifat.slushflicks.repository.tv.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.repository.tv.TvHomeRepository
import com.sifat.slushflicks.ui.state.DataState
import javax.inject.Inject

class TvHomeRepositoryImpl
@Inject constructor(
    private val dataManager: DataManager
) : TvHomeRepository {
    override fun getTvCollection(): LiveData<DataState<List<CollectionModel>>> {
        return dataManager.getTvCollections()
    }
}