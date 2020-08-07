package com.sifat.slushflicks.repository.tv.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_RECOMMENDATION_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_SIMILAR_API_TAG
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.data.pager.factory.TvReviewDataFactory
import com.sifat.slushflicks.data.pager.source.TvReviewDataSource
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvCollectionModel
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.resource.impl.SimilarTvShowNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvCastNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResource.RequestModel
import com.sifat.slushflicks.repository.resource.impl.TvVideoNetworkResource
import com.sifat.slushflicks.repository.tv.TvDetailsRepository
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.Label
import com.sifat.slushflicks.utils.Label.Companion.RECOMMENDATION_LABEL
import com.sifat.slushflicks.utils.Label.Companion.SIMILAR_LABEL
import com.sifat.slushflicks.utils.PAGE_SIZE
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class TvDetailsRepositoryImpl @Inject constructor(
    private val tvService: TvService,
    private val dataManager: DataManager,
    @Named(NAME_API_KEY)
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager,
    private val jobManager: JobManager,
    private val dispatcher: CoroutineDispatcher = IO
) : TvDetailsRepository {
    override fun getTvShowDetails(tvShowId: Long): LiveData<DataState<TvModel>> {
        return TvDetailsNetworkResource(
            dataManager = dataManager,
            tvService = tvService,
            request = RequestModel(apiKey, tvShowId),
            networkStateManager = networkStateManager,
            jobManager = jobManager,
            dispatcher = dispatcher
        ).asLiveData()
    }

    override fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): LiveData<DataState<String>> {
        return TvVideoNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = TvVideoNetworkResource.RequestModel(apiKey, tvShowId, seasonNumber),
            networkStateManager = networkStateManager,
            jobManager = jobManager,
            dispatcher = dispatcher
        ).asLiveData()
    }

    override fun getTvShowCast(movieId: Long): LiveData<DataState<Int>> {
        return TvCastNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = TvCastNetworkResource.RequestModel(apiKey, movieId),
            networkStateManager = networkStateManager,
            jobManager = jobManager,
            dispatcher = dispatcher
        ).asLiveData()
    }

    override fun getSimilarTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
        return SimilarTvShowNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = SimilarTvShowNetworkResource.RequestModel(
                apiKey = apiKey,
                tvShowId = movieId,
                apiTag = TV_SHOW_SIMILAR_API_TAG,
                relationType = SIMILAR_LABEL
            ),
            networkStateManager = networkStateManager,
            jobManager = jobManager,
            dispatcher = dispatcher
        ).asLiveData()
    }

    override fun getRecommendationTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>> {
        return SimilarTvShowNetworkResource(
            tvService = tvService,
            dataManager = dataManager,
            requestModel = SimilarTvShowNetworkResource.RequestModel(
                apiKey = apiKey,
                tvShowId = movieId,
                apiTag = TV_SHOW_RECOMMENDATION_API_TAG,
                relationType = RECOMMENDATION_LABEL
            ),
            networkStateManager = networkStateManager,
            jobManager = jobManager,
            dispatcher = dispatcher
        ).asLiveData()
    }

    override fun getTvShowReviews(movieId: Long): LiveData<DataState<PagedList<ReviewModel>>> {
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

    override fun updateRecentTvShow(tvShowId: Long) {
        val time = (System.currentTimeMillis() / 1000).toInt()
        CoroutineScope(dispatcher).launch {
            dataManager.insertNewTvCollection(
                TvCollectionModel(
                    collection = Label.RECENTLY_VISITED_TV_SHOW,
                    id = tvShowId,
                    index = -1 * time // Reversing the order
                )
            )
        }
    }

    override fun cancelAllJobs() {
        jobManager.cancelActiveJobs()
    }
}