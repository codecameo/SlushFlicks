package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sifat.slushflicks.model.ReviewModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.ui.state.DataState

interface TvDetailsRepository {
    fun getTvShowDetails(tvShowId: Long): LiveData<DataState<TvModel>>

    fun getTvShowVideo(tvShowId: Long, seasonNumber: Int): LiveData<DataState<String>>

    fun getTvShowCast(movieId: Long): LiveData<DataState<Int>>

    fun getSimilarTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>>

    fun getRecommendationTvShows(movieId: Long): LiveData<DataState<List<ShowModelMinimal>>>

    fun getTvShowReviews(movieId: Long): LiveData<DataState<PagedList<ReviewModel>>>
}