package com.sifat.slushflicks.utils

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
    Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }
    Mockito.`when`(pagedList.size).thenReturn(list.size)
    return pagedList
}

fun <K, T> getFakeMovieDataSource(list: List<T>): DataSource.Factory<K, T> {
    return object : DataSource.Factory<K, T>() {
        override fun create(): DataSource<K, T> {
            return object : PageKeyedDataSource<K, T>() {
                override fun loadInitial(
                    params: LoadInitialParams<K>,
                    callback: LoadInitialCallback<K, T>
                ) {
                    callback.onResult(list, null, null)
                }

                override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<K, T>) {
                    TODO("Not yet implemented")
                }

                override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<K, T>) {
                    TODO("Not yet implemented")
                }
            }
        }
    }
}