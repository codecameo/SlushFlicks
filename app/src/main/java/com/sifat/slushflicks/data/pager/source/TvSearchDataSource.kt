package com.sifat.slushflicks.data.pager.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sifat.slushflicks.api.home.search.SearchService
import com.sifat.slushflicks.api.home.tv.model.TvApiModel
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvSearchDataSource(
    private val searchService: SearchService,
    private val requestModel: RequestModel
) : PageKeyedDataSource<Long, TvApiModel>() {

    companion object {
        private const val TAG = "TvSearchDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, TvApiModel>
    ) {
        searchService.getSearchTvShows(
            query = requestModel.query,
            apiKey = requestModel.apiKey,
            page = 1
        ).enqueue(object : Callback<TvListApiModel> {
            override fun onResponse(
                call: Call<TvListApiModel>,
                response: Response<TvListApiModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.run {
                        val nextPage = if (totalPages > 1) 2L else null
                        callback.onResult(results, null, nextPage)
                    }
                } else {
                    Log.e(TAG, "Search failed")
                }
            }

            override fun onFailure(call: Call<TvListApiModel>, t: Throwable) {
                Log.e(TAG, "Search failed ex ${t.printStackTrace()}")
            }
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, TvApiModel>) {
        val page = params.key ?: return
        searchService.getSearchTvShows(
            query = requestModel.query,
            apiKey = requestModel.apiKey,
            page = 1
        ).enqueue(object : Callback<TvListApiModel> {
            override fun onResponse(
                call: Call<TvListApiModel>,
                response: Response<TvListApiModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.run {
                        val nextPage = if (totalPages > page) page + 1 else null
                        callback.onResult(results, nextPage)
                    }
                } else {
                    Log.e(TAG, "Search failed")
                }
            }

            override fun onFailure(call: Call<TvListApiModel>, t: Throwable) {
                Log.e(TAG, "Search failed ex ${t.printStackTrace()}")
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, TvApiModel>) {
    }

    data class RequestModel(val apiKey: String, val query: String)
}