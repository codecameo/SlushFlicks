package com.sifat.slushflicks.data.pager.factory

import androidx.paging.DataSource
import com.sifat.slushflicks.api.home.search.SearchService
import com.sifat.slushflicks.api.home.tv.model.TvApiModel
import com.sifat.slushflicks.data.pager.source.TvSearchDataSource

class TvSearchDataFactory(
    private val searchService: SearchService,
    private val requestModel: TvSearchDataSource.RequestModel
) : DataSource.Factory<Long, TvApiModel>() {
    override fun create(): DataSource<Long, TvApiModel> {
        return TvSearchDataSource(
            searchService = searchService,
            requestModel = requestModel
        )
    }
}