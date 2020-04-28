package com.example.slushflicks.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.repository.GenreRepository
import com.example.slushflicks.ui.state.DataState
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