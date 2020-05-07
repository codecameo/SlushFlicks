package com.sifat.slushflicks.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagedList.BoundaryCallback
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

interface MovieListRepository {

    val collection: String

    fun getMovieList(nextPage: Int): LiveData<DataState<Int>>

    fun getPagingMovieList(boundaryCallback: BoundaryCallback<ShowModelMinimal>): LiveData<DataState<PagedList<ShowModelMinimal>>>
}