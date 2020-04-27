package com.example.slushflicks.di.home

import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.di.constant.NAME_API_KEY
import com.example.slushflicks.repository.TrendingRepository
import com.example.slushflicks.utils.api.NetworkStateManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun getMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @HomeScope
    @Provides
    fun getTrendingRepository(
        movieService: MovieService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): TrendingRepository {
        return TrendingRepository(movieService, apiKey, dataManager, networkStateManager)
    }

}