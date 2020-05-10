package com.sifat.slushflicks.repository.search.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PagedList.BoundaryCallback
import androidx.paging.toLiveData
import com.sifat.slushflicks.api.home.search.SearchService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.data.pager.factory.MovieSearchDataFactory
import com.sifat.slushflicks.data.pager.factory.TvSearchDataFactory
import com.sifat.slushflicks.data.pager.source.MovieSearchDataSource
import com.sifat.slushflicks.data.pager.source.TvSearchDataSource.RequestModel
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.search.SearchRepository
import com.sifat.slushflicks.ui.helper.getMovieMinimalApiModel
import com.sifat.slushflicks.ui.helper.getTvShowMinimalApiModel
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.Label
import com.sifat.slushflicks.utils.PAGE_SIZE
import javax.inject.Inject
import javax.inject.Named

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService,
    @Named(NAME_API_KEY)
    private val apiKey: String,
    private val dataManager: DataManager
) : SearchRepository {

    private var recentMovieLiveData: LiveData<PagedList<ShowModelMinimal>>? = null
    private var movieDataFactory: DataSource.Factory<Int, ShowModelMinimal>? = null
    private var recentTvShowLiveData: LiveData<PagedList<ShowModelMinimal>>? = null
    private var tvShowDataFactory: DataSource.Factory<Int, ShowModelMinimal>? = null

    override fun searchTvShows(
        query: String,
        boundaryCallback: BoundaryCallback<ShowModelMinimal>
    ): LiveData<DataState<PagedList<ShowModelMinimal>>> {
        val dataSource = TvSearchDataFactory(
            searchService = searchService,
            requestModel = RequestModel(apiKey, query)
        ).map { apiModel ->
            getTvShowMinimalApiModel(apiModel, dataManager.getGenres())
        }.toLiveData(
            config = getPageConfig(),
            boundaryCallback = boundaryCallback
        )

        return getTransformedDataSource(dataSource)
    }

    override fun searchMovies(
        query: String,
        boundaryCallback: BoundaryCallback<ShowModelMinimal>
    ): LiveData<DataState<PagedList<ShowModelMinimal>>> {
        val dataSource = MovieSearchDataFactory(
            searchService = searchService,
            requestModel = MovieSearchDataSource.RequestModel(apiKey, query)
        ).map { apiModel ->
            getMovieMinimalApiModel(apiModel, dataManager.getGenres())
        }.toLiveData(
            config = getPageConfig(),
            boundaryCallback = boundaryCallback
        )

        return getTransformedDataSource(dataSource)
    }

    override fun getRecentMovieList(boundaryCallback: BoundaryCallback<ShowModelMinimal>): LiveData<DataState<PagedList<ShowModelMinimal>>> {
        // Create data-source if it's been not created already
        movieDataFactory =
            movieDataFactory ?: dataManager.getPagingMovies(Label.RECENTLY_VISITED_MOVIE)
        recentMovieLiveData = movieDataFactory!!.toLiveData(
            config = getPageConfig(),
            boundaryCallback = boundaryCallback
        )
        return getTransformedDataSource(recentMovieLiveData!!)
    }

    override fun getRecentTvShowList(boundaryCallback: BoundaryCallback<ShowModelMinimal>): LiveData<DataState<PagedList<ShowModelMinimal>>> {
        // Create data-source if it's been not created already
        tvShowDataFactory =
            tvShowDataFactory ?: dataManager.getPagingTvShows(Label.RECENTLY_VISITED_TV_SHOW)
        recentTvShowLiveData = tvShowDataFactory!!.toLiveData(
            config = getPageConfig(),
            boundaryCallback = boundaryCallback
        )
        return getTransformedDataSource(recentTvShowLiveData!!)
    }

    private fun getTransformedDataSource(dataSource: LiveData<PagedList<ShowModelMinimal>>): LiveData<DataState<PagedList<ShowModelMinimal>>> {
        return Transformations.map(dataSource) { pagedList ->
            DataState.Success<PagedList<ShowModelMinimal>>(
                DataSuccessResponse(
                    data = pagedList
                )
            )
        }
    }

    private fun getPageConfig() = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPrefetchDistance(PAGE_SIZE / 2)
        .setEnablePlaceholders(false)
        .build()

    companion object {
        private const val TAG = "SearchRepositoryImpl"
    }
}