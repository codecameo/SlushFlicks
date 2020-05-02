package com.sifat.slushflicks.repository

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_RECOMMENDATION_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_SIMILAR_API_TAG
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.repository.resource.impl.CastNetworkResource
import com.sifat.slushflicks.repository.resource.impl.DetailsNetworkResource
import com.sifat.slushflicks.repository.resource.impl.DetailsNetworkResource.RequestModel
import com.sifat.slushflicks.repository.resource.impl.SimilarMoviesNetworkResource
import com.sifat.slushflicks.repository.resource.impl.VideoNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.Label.Companion.RECOMMENDATION_LABEL
import com.sifat.slushflicks.utils.Label.Companion.SIMILAR_LABEL
import com.sifat.slushflicks.utils.api.NetworkStateManager

class MovieDetailsRepository(
    private val movieService: MovieService,
    private val dataManager: DataManager,
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager
) {
    fun getMovieDetails(movieId: Long): LiveData<DataState<MovieModel>> {
        return DetailsNetworkResource(
            dataManager = dataManager,
            movieService = movieService,
            request = RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getMovieVideo(movieId: Long): LiveData<DataState<String>> {
        return VideoNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = VideoNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getMovieCast(movieId: Long): LiveData<DataState<Int>> {
        return CastNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = CastNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getSimilarMovies(movieId: Long): LiveData<DataState<List<MovieModelMinimal>>> {
        return SimilarMoviesNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = SimilarMoviesNetworkResource.RequestModel(
                apiKey = apiKey,
                movieId = movieId,
                apiTag = MOVIE_SIMILAR_API_TAG,
                relationType = SIMILAR_LABEL
            ),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getRecommendationMovies(movieId: Long): LiveData<DataState<List<MovieModelMinimal>>> {
        return SimilarMoviesNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = SimilarMoviesNetworkResource.RequestModel(
                apiKey = apiKey,
                movieId = movieId,
                apiTag = MOVIE_RECOMMENDATION_API_TAG,
                relationType = RECOMMENDATION_LABEL
            ),
            networkStateManager = networkStateManager
        ).asLiveData()
    }
}