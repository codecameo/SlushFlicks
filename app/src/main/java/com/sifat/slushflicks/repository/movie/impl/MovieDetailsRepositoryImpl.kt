package com.sifat.slushflicks.repository.movie.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_RECOMMENDATION_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_SIMILAR_API_TAG
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.data.pager.factory.MovieReviewDataFactory
import com.sifat.slushflicks.data.pager.source.MovieReviewDataSource
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.movie.MovieDetailsRepository
import com.sifat.slushflicks.repository.resource.impl.MovieCastNetworkResource
import com.sifat.slushflicks.repository.resource.impl.MovieDetailsNetworkResource
import com.sifat.slushflicks.repository.resource.impl.MovieDetailsNetworkResource.RequestModel
import com.sifat.slushflicks.repository.resource.impl.MovieVideoNetworkResource
import com.sifat.slushflicks.repository.resource.impl.SimilarMoviesNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.Label.Companion.RECOMMENDATION_LABEL
import com.sifat.slushflicks.utils.Label.Companion.SIMILAR_LABEL
import com.sifat.slushflicks.utils.PAGE_SIZE
import com.sifat.slushflicks.utils.api.NetworkStateManager
import javax.inject.Inject
import javax.inject.Named

class MovieDetailsRepositoryImpl
@Inject constructor(
    private val movieService: MovieService,
    private val dataManager: DataManager,
    @Named(NAME_API_KEY)
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager
) : MovieDetailsRepository {

    override fun getMovieDetails(movieId: Long): LiveData<DataState<MovieModel>> {
        return MovieDetailsNetworkResource(
            dataManager = dataManager,
            movieService = movieService,
            request = RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    override fun getMovieVideo(movieId: Long): LiveData<DataState<String>> {
        return MovieVideoNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = MovieVideoNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    override fun getMovieCast(movieId: Long): LiveData<DataState<Int>> {
        return MovieCastNetworkResource(
            movieService = movieService,
            dataManager = dataManager,
            requestModel = MovieCastNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    override fun getSimilarMovies(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
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

    override fun getRecommendationMovies(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
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

    override fun getReviews(movieId: Long): LiveData<DataState<PagedList<ReviewModel>>> {
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
    }
}