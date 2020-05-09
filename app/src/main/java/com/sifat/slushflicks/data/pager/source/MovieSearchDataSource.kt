package com.sifat.slushflicks.data.pager.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sifat.slushflicks.api.home.movie.model.MovieApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.api.home.search.SearchService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieSearchDataSource(
    private val searchService: SearchService,
    private val requestModel: RequestModel
) : PageKeyedDataSource<Long, MovieApiModel>() {
    companion object {
        private const val TAG = "MovieSearchDataSource"
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, MovieApiModel>
    ) {
        Log.d(TAG, "loadInitial: ")
        searchService.getSearchMovies(
            query = requestModel.query,
            apiKey = requestModel.apiKey,
            page = 1
        ).enqueue(object : Callback<MovieListApiModel> {
            override fun onResponse(
                call: Call<MovieListApiModel>,
                response: Response<MovieListApiModel>
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

            override fun onFailure(call: Call<MovieListApiModel>, t: Throwable) {
                Log.e(TAG, "Search failed ex ${t.printStackTrace()}")
            }
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, MovieApiModel>) {
        val page = params.key
        Log.d(TAG, "loadAfter: page $page")
        searchService.getSearchMovies(
            query = requestModel.query,
            apiKey = requestModel.apiKey,
            page = 1
        ).enqueue(object : Callback<MovieListApiModel> {
            override fun onResponse(
                call: Call<MovieListApiModel>,
                response: Response<MovieListApiModel>
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

            override fun onFailure(call: Call<MovieListApiModel>, t: Throwable) {
                Log.e(TAG, "Search failed ex ${t.printStackTrace()}")
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, MovieApiModel>) {
        Log.d(TAG, "loadBefore: ")
    }

    data class RequestModel(val apiKey: String, val query: String)
}