package com.sifat.slushflicks.repository.movie.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiTag.Companion.NOW_PLAYING_MOVIE_API_TAG
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.MovieListNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.Label.Companion.NOW_PLAYING_LABEL
import com.sifat.slushflicks.utils.api.NetworkStateManager
import javax.inject.Inject
import javax.inject.Named

class NowPlayingRepositoryImpl
@Inject constructor(
    private val movieService: MovieService,
    @Named(NAME_API_KEY)
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager,
    jobManager: JobManager,
    dataManager: DataManager
) : BaseMovieListRepository(dataManager, jobManager) {
    override val collection: String
        get() = NOW_PLAYING_LABEL

    override fun getMovieList(nextPage: Int): LiveData<DataState<Int>> {
        val requestModel = MovieListNetworkResource.RequestModel(
            page = nextPage,
            apiKey = apiKey,
            apiTag = NOW_PLAYING_MOVIE_API_TAG,
            collection = collection
        )
        val movieListNetworkResource = MovieListNetworkResource(
            requestModel = requestModel,
            movieService = movieService,
            networkStateManager = networkStateManager,
            dataManager = dataManager,
            jobManager = jobManager
        )
        return movieListNetworkResource.asLiveData()
    }
}