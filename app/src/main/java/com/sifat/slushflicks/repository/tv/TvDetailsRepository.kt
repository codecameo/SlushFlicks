package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResource.RequestModel
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager

class TvDetailsRepository(
    private val tvService: TvService,
    private val dataManager: DataManager,
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager
) {
    fun getTvShowDetails(tvShowId: Long): LiveData<DataState<TvModel>> {
        return TvDetailsNetworkResource(
            dataManager = dataManager,
            tvService = tvService,
            request = RequestModel(apiKey, tvShowId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    /*fun getTvShowVideo(movieId: Long): LiveData<DataState<String>> {
        return MovieVideoNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = MovieVideoNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getTvShowCast(movieId: Long): LiveData<DataState<Int>> {
        return MovieCastNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = MovieCastNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getSimilarTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
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

    fun getRecommendationTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
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

    fun getTvShowReviews(movieId: Long): LiveData<DataState<PagedList<ReviewModel>>> {
        val pageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE / 2)
            .build()
        val dataSource = MovieReviewDataFactory(
            movieService = movieService,
            requestModel = MovieReviewDataSource.RequestModel(apiKey, movieId)
        ).toLiveData(pageConfig)
        return Transformations.map(
            dataSource
        ) { pagedList ->
            DataState.Success<PagedList<ReviewModel>>(
                DataSuccessResponse(
                    data = pagedList
                )
            )
        }
    }*/
}