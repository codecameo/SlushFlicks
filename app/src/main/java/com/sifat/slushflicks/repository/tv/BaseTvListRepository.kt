package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.PAGE_SIZE
import com.sifat.slushflicks.utils.api.NetworkStateManager

abstract class BaseTvListRepository(
    protected val tvService: TvService,
    protected val apiKey: String,
    protected val dataManager: DataManager,
    protected val networkStateManager: NetworkStateManager
) {

    abstract val collection: String

    abstract fun getTvList(nextPage: Int): LiveData<DataState<Int>>

    fun getPagingTvList(boundaryCallback: PagedList.BoundaryCallback<ShowModelMinimal>): LiveData<DataState<PagedList<ShowModelMinimal>>> {
        val pageConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE / 2)
            .build()
        val dataSource = dataManager.getPagingTvShows(collection).toLiveData(
            config = pageConfig,
            boundaryCallback = boundaryCallback
        )
        return Transformations.map(dataSource) { pagedList ->
            DataState.Success<PagedList<ShowModelMinimal>>(
                DataSuccessResponse(
                    data = pagedList
                )
            )
        }
    }
}