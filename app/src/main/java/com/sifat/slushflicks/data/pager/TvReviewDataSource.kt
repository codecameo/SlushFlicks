package com.sifat.slushflicks.data.pager

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sifat.slushflicks.api.details.model.ReviewListApiModel
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.model.ReviewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvReviewDataSource(
    private val tvService: TvService,
    private val requestModel: RequestModel
) : PageKeyedDataSource<Long, ReviewModel>() {
    val TAG = "ReviewDataSource"

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ReviewModel>
    ) {
        tvService.getTvShowReviews(
            tvShowId = requestModel.movieId,
            apiKey = requestModel.apiKey,
            page = 1
        ).enqueue(object : Callback<ReviewListApiModel> {
            override fun onResponse(
                call: Call<ReviewListApiModel>,
                response: Response<ReviewListApiModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.run {
                        val nextPage = if (totalPages > 1) 2L else null
                        callback.onResult(results, null, nextPage)
                    }
                } else {
                    Log.e(TAG, "Review failed")
                }
            }

            override fun onFailure(call: Call<ReviewListApiModel>, t: Throwable) {
                Log.e(TAG, "Review failed ex ${t.printStackTrace()}")
            }
        })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, ReviewModel>) {
        val page = params.key ?: return
        tvService.getTvShowReviews(
            tvShowId = requestModel.movieId,
            apiKey = requestModel.apiKey,
            page = page.toInt()
        ).enqueue(object : Callback<ReviewListApiModel> {
            override fun onResponse(
                call: Call<ReviewListApiModel>,
                response: Response<ReviewListApiModel>
            ) {
                if (response.isSuccessful) {
                    response.body()?.run {
                        val nextPage = if (totalPages > page) page + 1 else null
                        callback.onResult(results, nextPage)
                    }
                } else {
                    Log.e(TAG, "Review failed")
                }
            }

            override fun onFailure(call: Call<ReviewListApiModel>, t: Throwable) {
                Log.e(TAG, "Review failed ex ${t.printStackTrace()}")
            }
        })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ReviewModel>) {

    }

    data class RequestModel(val apiKey: String, val movieId: Long)
}