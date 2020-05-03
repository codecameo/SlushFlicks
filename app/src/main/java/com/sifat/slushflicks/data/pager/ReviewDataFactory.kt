package com.sifat.slushflicks.data.pager

import androidx.paging.DataSource
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.model.ReviewModel

class ReviewDataFactory(
    private val movieService: MovieService,
    private val requestModel: ReviewDataSource.RequestModel
) : DataSource.Factory<Long, ReviewModel>() {
    override fun create(): DataSource<Long, ReviewModel> {
        return ReviewDataSource(movieService, requestModel)
    }
}