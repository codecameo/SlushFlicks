package com.sifat.slushflicks.api.home.search

import com.google.gson.Gson
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.utils.api.*
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.ResponseBody.create
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.error
import retrofit2.Response.success

class SearchServiceFake(private val gson: Gson) : SearchService {
    var errorCode: Int? = null

    override fun getSearchMovies(
        query: String,
        apiKey: String,
        page: Int,
        tag: String
    ): Call<MovieListApiModel> {
        return object : Call<MovieListApiModel> {
            override fun enqueue(callback: Callback<MovieListApiModel>) {
                callback.onResponse(this, execute())
            }

            override fun isExecuted() = false

            override fun clone() = this

            override fun isCanceled() = false

            override fun cancel() {}

            override fun execute(): Response<MovieListApiModel> {
                return when (errorCode) {
                    RESOURCE_NOT_FOUND -> error<MovieListApiModel>(
                        RESOURCE_NOT_FOUND, create(
                            MediaType.parse("text"), noResourceFound
                        )
                    )
                    UNAUTHORIZED -> error<MovieListApiModel>(
                        UNAUTHORIZED, create(
                            MediaType.parse("text"), unauthResponse
                        )
                    )
                    else -> success(getMovieListSuccessResponse(page, query))
                }
            }

            override fun request() = Request.Builder().build()
        }
    }

    override fun getSearchTvShows(
        query: String,
        apiKey: String,
        page: Int,
        tag: String
    ): Call<TvListApiModel> {
        return object : Call<TvListApiModel> {
            override fun enqueue(callback: Callback<TvListApiModel>) {
                callback.onResponse(this, execute())
            }

            override fun isExecuted() = false

            override fun clone() = this

            override fun isCanceled() = false

            override fun cancel() {}

            override fun execute(): Response<TvListApiModel> {
                return when (errorCode) {
                    RESOURCE_NOT_FOUND -> error<TvListApiModel>(
                        RESOURCE_NOT_FOUND, create(
                            MediaType.parse("text"), noResourceFound
                        )
                    )
                    UNAUTHORIZED -> error<TvListApiModel>(
                        UNAUTHORIZED, create(
                            MediaType.parse("text"), unauthResponse
                        )
                    )
                    else -> success(getTvShowListSuccessResponse(page, query))
                }
            }

            override fun request() = Request.Builder().build()
        }
    }

    private fun getMovieListSuccessResponse(page: Int, query: String): MovieListApiModel {
        return gson.fromJson(
            when {
                query != invalidSearchKey && page == 1 -> moviePage1
                query != invalidSearchKey && page == 2 -> moviePage2
                else -> moviePageInvalid
            }, MovieListApiModel::class.java
        )
    }

    private fun getTvShowListSuccessResponse(page: Int, query: String): TvListApiModel {
        return gson.fromJson(
            when {
                query != invalidSearchKey && page == 1 -> tvShowPage1
                query != invalidSearchKey && page == 2 -> tvShowPage2
                else -> tvShowInvalidPage
            }, TvListApiModel::class.java
        )
    }

    companion object {
        const val invalidSearchKey = "invalid"
    }
}