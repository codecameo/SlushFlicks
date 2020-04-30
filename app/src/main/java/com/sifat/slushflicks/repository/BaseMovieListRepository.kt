package com.sifat.slushflicks.repository

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.PagedList.BoundaryCallback
import androidx.paging.toLiveData
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.PAGE_SIZE
import com.sifat.slushflicks.utils.api.NetworkStateManager

abstract class BaseMovieListRepository(
    protected val movieService: MovieService,
    protected val apiKey: String,
    protected val dataManager: DataManager,
    protected val networkStateManager: NetworkStateManager
) {

    abstract val collection: String

    abstract fun getMovieList(nextPage: Int): LiveData<DataState<Int>>

    fun getPagingMovieList(boundaryCallback: BoundaryCallback<MovieModelMinimal>): LiveData<DataState<PagedList<MovieModelMinimal>>> {
        val pageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE / 2)
            .build()
        val dataSource = dataManager.getPagingMovies(collection).toLiveData(
            config = pageConfig,
            boundaryCallback = boundaryCallback
        )
        return Transformations.map(
            dataSource,
            Function<PagedList<MovieModelMinimal>, DataState<PagedList<MovieModelMinimal>>> { pagedList ->
                DataState.Success<PagedList<MovieModelMinimal>>(
                    DataSuccessResponse(
                        data = pagedList
                    )
                )
            })
    }
}