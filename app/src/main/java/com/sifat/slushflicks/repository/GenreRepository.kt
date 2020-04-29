package com.sifat.slushflicks.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sifat.slushflicks.api.home.genre.GenreService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.repository.resource.impl.GenreNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GenreRepository(
    private val genreService: GenreService,
    private val dataManager: DataManager,
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager
) {

    fun setGenreList(): LiveData<DataState<List<GenreModel>>> {
        val mediatorLiveData = MediatorLiveData<DataState<List<GenreModel>>>()
        val dataResponse = GenreNetworkResource(
            dataManager = dataManager
        ).asLiveData()

        mediatorLiveData.addSource(
            dataResponse
        ) { response ->
            mediatorLiveData.removeSource(dataResponse)
            when (response) {
                is DataState.Success -> {
                    // Initialize genre map in local dataManager
                    dataManager.initGenres(response.dataResponse.data)
                    mediatorLiveData.value = response
                }
                is DataState.Error -> {
                    mediatorLiveData.value = response
                }
            }
        }
        return mediatorLiveData
    }

    fun updateGenres() {
        if (!networkStateManager.isOnline()) return
        /**
         * Two parallel corotuine was launched to update genre database.
         * Ran it on global scope so that it finishes it's task
         * even if user leaves the app or navigates to another screen
         * */
        GlobalScope.launch(IO) {
            launch {
                //TODO
                try {
                    val response = genreService.getTvGenre(apiKey)
                    if (response.isSuccessful) {
                        response.body()?.run {
                            dataManager.saveGenre(genres)
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            launch {
                try {
                    val response = genreService.getMovieGenre(apiKey)
                    if (response.isSuccessful) {
                        response.body()?.run {
                            dataManager.saveGenre(genres)
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }
}