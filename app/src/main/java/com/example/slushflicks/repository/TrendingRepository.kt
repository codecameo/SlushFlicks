package com.example.slushflicks.repository

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.home.movie.MovieListApiModel
import com.example.slushflicks.api.home.movie.MovieService

class TrendingRepository(private val movieService: MovieService, private val apiKey : String) {

    fun getMovieList(nextPage: Int) : LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getTrendingMovies(apiKey = apiKey)
    }
}