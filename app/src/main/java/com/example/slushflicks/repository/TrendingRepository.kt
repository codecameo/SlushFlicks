package com.example.slushflicks.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.switchMap
import com.example.slushflicks.api.ApiEmptyResponse
import com.example.slushflicks.api.ApiErrorResponse
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiSuccessResponse
import com.example.slushflicks.api.home.movie.MovieListApiModel
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.ui.helper.getMetaData
import com.example.slushflicks.ui.helper.getMovieList
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction.FetchMovieListDataAction
import com.example.slushflicks.ui.state.DataResponse
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.ui.state.DataSuccessResponse

/**
 * TODO Create repository interface and Implement that
 * */
class TrendingRepository(private val movieService: MovieService, private val apiKey: String) {

    fun getMovieList(nextPage: Int): LiveData<FetchMovieListDataAction> {

        /**
         * Change Api response to data response which viewModel will use to
         * show relevant information in view
         * */
        return switchMap(
            movieService.getTrendingMovies(
                apiKey = apiKey,
                page = nextPage
            )
        ) { apiResponse ->
            object : LiveData<FetchMovieListDataAction>() {
                override fun onActive() {
                    when (apiResponse) {
                        is ApiSuccessResponse -> {
                            val dataSuccessResponse = DataSuccessResponse(
                                data = getMovieList(apiResponse.data?.results),
                                metaData = getMetaData(apiResponse.data),
                                message = apiResponse.message
                            )
                            val successState = DataState.Success(dataSuccessResponse)
                            value = FetchMovieListDataAction(dataState = successState)
                        }
                        is ApiErrorResponse -> {
                            //TODO Handle error states
                        }
                        is ApiEmptyResponse -> {
                            //TODO Handle empty states
                        }
                    }
                }
            }
        }
    }
}