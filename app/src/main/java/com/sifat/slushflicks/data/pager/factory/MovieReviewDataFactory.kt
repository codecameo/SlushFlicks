package com.sifat.slushflicks.data.pager.factory

import androidx.paging.DataSource
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.pager.source.MovieReviewDataSource
import com.sifat.slushflicks.model.ReviewModel

class MovieReviewDataFactory(
    private val movieService: MovieService,
    private val requestModel: MovieReviewDataSource.RequestModel
) : DataSource.Factory<Long, ReviewModel>() {
    override fun create(): DataSource<Long, ReviewModel> {
        return MovieReviewDataSource(
            movieService,
            requestModel
        )
    }
}