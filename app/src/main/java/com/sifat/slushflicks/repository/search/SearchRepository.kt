package com.sifat.slushflicks.repository.search

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

interface SearchRepository {
    fun searchMovies(
        query: String,
        boundaryCallback: PagedList.BoundaryCallback<ShowModelMinimal>
    ): LiveData<DataState<PagedList<ShowModelMinimal>>>

    fun searchTvShows(
        query: String,
        boundaryCallback: PagedList.BoundaryCallback<ShowModelMinimal>
    ): LiveData<DataState<PagedList<ShowModelMinimal>>>
}