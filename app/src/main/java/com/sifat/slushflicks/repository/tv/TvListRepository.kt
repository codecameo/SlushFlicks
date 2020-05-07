package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

interface TvListRepository {

    val collection: String

    fun getTvList(nextPage: Int): LiveData<DataState<Int>>

    fun getPagingTvList(boundaryCallback: PagedList.BoundaryCallback<ShowModelMinimal>): LiveData<DataState<PagedList<ShowModelMinimal>>>
}