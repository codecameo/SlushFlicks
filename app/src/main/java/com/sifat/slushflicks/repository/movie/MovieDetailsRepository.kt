package com.sifat.slushflicks.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Long): LiveData<DataState<MovieModel>>

    fun getMovieVideo(movieId: Long): LiveData<DataState<String>>

    fun getMovieCast(movieId: Long): LiveData<DataState<Int>>

    fun getSimilarMovies(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>>

    fun getRecommendationMovies(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>>

    fun getReviews(movieId: Long): LiveData<DataState<PagedList<ReviewModel>>>
}