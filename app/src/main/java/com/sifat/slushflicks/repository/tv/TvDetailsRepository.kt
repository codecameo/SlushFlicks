package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_RECOMMENDATION_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_SIMILAR_API_TAG
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.data.pager.TvReviewDataFactory
import com.sifat.slushflicks.data.pager.TvReviewDataSource
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.resource.impl.SimilarTvShowNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvCastNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResource.RequestModel
import com.sifat.slushflicks.repository.resource.impl.TvVideoNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.Label.Companion.RECOMMENDATION_LABEL
import com.sifat.slushflicks.utils.Label.Companion.SIMILAR_LABEL
import com.sifat.slushflicks.utils.PAGE_SIZE
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

    fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): LiveData<DataState<String>> {
        return TvVideoNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = TvVideoNetworkResource.RequestModel(apiKey, tvShowId, seasonNumber),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getTvShowCast(movieId: Long): LiveData<DataState<Int>> {
        return TvCastNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = TvCastNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getSimilarTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
        return SimilarTvShowNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = SimilarTvShowNetworkResource.RequestModel(
                apiKey = apiKey,
                tvShowId = movieId,
                apiTag = TV_SHOW_SIMILAR_API_TAG,
                relationType = SIMILAR_LABEL
            ),
            networkStateManager = networkStateManager
        ).asLiveData()
    }

    fun getRecommendationTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
        return SimilarTvShowNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = SimilarTvShowNetworkResource.RequestModel(
                apiKey = apiKey,
                tvShowId = movieId,
                apiTag = TV_SHOW_RECOMMENDATION_API_TAG,
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
        val dataSource = TvReviewDataFactory(
            tvService = tvService,
            requestModel = TvReviewDataSource.RequestModel(apiKey, movieId)
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