package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataState

interface TvHomeRepository {
    fun getTvCollection(): LiveData<DataState<List<CollectionModel>>>
}