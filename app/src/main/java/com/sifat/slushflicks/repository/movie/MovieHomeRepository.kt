package com.sifat.slushflicks.repository.movie

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataState

interface MovieHomeRepository {
    fun getMovieCollection(): LiveData<DataState<List<CollectionModel>>>
}