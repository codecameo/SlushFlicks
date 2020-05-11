package com.sifat.slushflicks.di.home.search

import com.sifat.slushflicks.api.home.search.SearchService
import com.sifat.slushflicks.repository.search.SearchRepository
import com.sifat.slushflicks.repository.search.impl.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [InnerModule::class])
abstract class SearchModule {
    @SearchScope
    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}

@Module
class InnerModule {
    @SearchScope
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)
}