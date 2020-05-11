package com.sifat.slushflicks.repository.movie.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.home.movie.MovieScope
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.repository.movie.MovieHomeRepository
import com.sifat.slushflicks.ui.state.DataState
import javax.inject.Inject

@MovieScope
class MovieHomeRepositoryImpl
@Inject constructor(
    private val dataManager: DataManager
) : MovieHomeRepository {
    override fun getMovieCollection(): LiveData<DataState<List<CollectionModel>>> {
        return dataManager.getMovieCollections()
    }
}