package com.sifat.slushflicks.repository.genre

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.ui.state.DataState

interface GenreRepository {
    fun setGenreList(): LiveData<DataState<List<GenreModel>>>

    fun updateGenres()
}