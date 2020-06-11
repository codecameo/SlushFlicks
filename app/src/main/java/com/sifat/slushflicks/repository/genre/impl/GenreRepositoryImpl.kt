package com.sifat.slushflicks.repository.genre.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.splash.SplashScope
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.repository.genre.GenreRepository
import com.sifat.slushflicks.repository.resource.impl.GenreNetworkResource
import com.sifat.slushflicks.repository.resource.impl.MovieGenreNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvGenreNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import javax.inject.Inject

@SplashScope
class GenreRepositoryImpl
@Inject constructor(
    private val dataManager: DataManager
) : GenreRepository {

    @Inject
    lateinit var genreNetworkResource: GenreNetworkResource

    @Inject
    lateinit var tvGenreNetworkResource: TvGenreNetworkResource

    @Inject
    lateinit var movieGenreNetworkResource: MovieGenreNetworkResource

    override fun setGenreList(): LiveData<DataState<List<GenreModel>>> {
        val mediatorLiveData = MediatorLiveData<DataState<List<GenreModel>>>()
        val dataResponse = genreNetworkResource.asLiveData()

        mediatorLiveData.addSource(dataResponse) { response ->
            mediatorLiveData.removeSource(dataResponse)
            when (response) {
                is Success -> {
                    // Initialize genre map in local dataManager
                    dataManager.initGenres(response.dataResponse.data)
                    mediatorLiveData.value = response
                }
                is Error -> {
                    mediatorLiveData.value = response
                }
            }
        }
        return mediatorLiveData
    }

    override fun updateGenres(): LiveData<DataState<List<GenreModel>>> {
        val result = MediatorLiveData<DataState<List<GenreModel>>>()
        val tvLive = tvGenreNetworkResource.asLiveData()
        result.addSource(tvLive) { data ->
            result.removeSource(tvLive)
            result.value = data
        }
        val movieLive = movieGenreNetworkResource.asLiveData()
        result.addSource(movieLive) { data ->
            result.removeSource(movieLive)
            result.value = data
        }
        return result
    }
}