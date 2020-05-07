package com.sifat.slushflicks.repository.movie.impl

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.PagedList.BoundaryCallback
import androidx.paging.toLiveData
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.movie.MovieListRepository
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.PAGE_SIZE
import com.sifat.slushflicks.utils.api.NetworkStateManager

abstract class BaseMovieListRepository(
    protected val movieService: MovieService,
    protected val apiKey: String,
    protected val dataManager: DataManager,
    protected val networkStateManager: NetworkStateManager
) : MovieListRepository {
    override fun getPagingMovieList(boundaryCallback: BoundaryCallback<ShowModelMinimal>): LiveData<DataState<PagedList<ShowModelMinimal>>> {
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
            Function<PagedList<ShowModelMinimal>, DataState<PagedList<ShowModelMinimal>>> { pagedList ->
                DataState.Success<PagedList<ShowModelMinimal>>(
                    DataSuccessResponse(
                        data = pagedList
                    )
                )
            })
    }
}