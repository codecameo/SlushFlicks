package com.sifat.slushflicks.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.repository.genre.GenreRepository
import com.sifat.slushflicks.ui.state.DataState
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {

    fun updateGenres() {
        genreRepository.updateGenres()
    }

    fun setGenreList(): LiveData<DataState<List<GenreModel>>> {
        return genreRepository.setGenreList()
    }
}