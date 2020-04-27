package com.example.slushflicks.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiSuccessResponse
import com.example.slushflicks.api.home.genre.GenreService
import com.example.slushflicks.api.home.genre.model.GenreListApiModel
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.repository.resource.impl.GenreNetworkResource
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

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
                val response : Response<GenreListApiModel> =  genreService.getTvGenre(apiKey)
                if (response.isSuccessful) {
                    response.body()?.run {
                        dataManager.saveGenre(genres)
                    }
                }
            }
            launch {
                val response : Response<GenreListApiModel> =  genreService.getMovieGenre(apiKey)
                if (response.isSuccessful) {
                    response.body()?.run {
                        dataManager.saveGenre(genres)
                    }
                }
            }
        }
    }
}