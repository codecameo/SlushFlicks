package com.sifat.slushflicks.data.pager

import androidx.paging.DataSource
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.model.ReviewModel

class TvReviewDataFactory(
    private val tvService: TvService,
    private val requestModel: TvReviewDataSource.RequestModel
) : DataSource.Factory<Long, ReviewModel>() {
    override fun create(): DataSource<Long, ReviewModel> {
        return TvReviewDataSource(tvService, requestModel)
    }
}