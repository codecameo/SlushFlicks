package com.sifat.slushflicks.repository.movie.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiTag.Companion.POPULAR_MOVIE_API_TAG
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.MovieListNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.Label.Companion.POPULAR_LABEL
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Named

class PopularMovieRepositoryImpl
@Inject constructor(
    private val movieService: MovieService,
    @Named(NAME_API_KEY)
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager,
    private val dispatcher: CoroutineDispatcher,
    dataManager: DataManager,
    jobManager: JobManager
) : BaseMovieListRepository(dataManager, jobManager) {
    override val collection: String
        get() = POPULAR_LABEL

    override fun getMovieList(nextPage: Int): LiveData<DataState<Int>> {
        val requestModel = MovieListNetworkResource.RequestModel(
            page = nextPage,
            apiKey = apiKey,
            collection = collection,
            apiTag = POPULAR_MOVIE_API_TAG
        )
        val movieListNetworkResource = MovieListNetworkResource(
            requestModel = requestModel,
            movieService = movieService,
            networkStateManager = networkStateManager,
            dataManager = dataManager,
            jobManager = jobManager,
            dispatcher = dispatcher
        )
        return movieListNetworkResource.asLiveData()
    }
}