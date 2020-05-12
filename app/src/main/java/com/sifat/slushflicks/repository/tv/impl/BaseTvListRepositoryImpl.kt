package com.sifat.slushflicks.repository.tv.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.tv.TvListRepository
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.PAGE_SIZE

abstract class BaseTvListRepositoryImpl(
    protected val dataManager: DataManager,
    protected val jobManager: JobManager
) : TvListRepository {

    override fun getPagingTvList(boundaryCallback: PagedList.BoundaryCallback<ShowModelMinimal>): LiveData<DataState<PagedList<ShowModelMinimal>>> {
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

    override fun cancelAllJobs() {
        jobManager.cancelActiveJobs()
    }
}