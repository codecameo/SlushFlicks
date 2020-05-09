package com.sifat.slushflicks.data.pager.factory

import androidx.paging.DataSource
import com.sifat.slushflicks.api.home.movie.model.MovieApiModel
import com.sifat.slushflicks.api.home.search.SearchService
import com.sifat.slushflicks.data.pager.source.MovieSearchDataSource
import com.sifat.slushflicks.data.pager.source.MovieSearchDataSource.RequestModel

class MovieSearchDataFactory(
    private val searchService: SearchService,
    private val requestModel: RequestModel
) : DataSource.Factory<Long, MovieApiModel>() {
    override fun create(): DataSource<Long, MovieApiModel> {
        return MovieSearchDataSource(
            searchService = searchService,
            requestModel = requestModel
        )
    }
}