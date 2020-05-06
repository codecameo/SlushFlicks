package com.sifat.slushflicks.di.home.movie

import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.repository.movie.*
import com.sifat.slushflicks.utils.api.NetworkStateManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MovieModule {

    @HomeScope
    @Provides
    fun provideMovieHomeRepository(
        dataManager: DataManager
    ): MovieHomeRepository {
        return MovieHomeRepository(
            dataManager
        )
    }

    @HomeScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @HomeScope
    @Provides
    fun provideTrendingMovieRepository(
        movieService: MovieService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): TrendingMovieRepository {
        return TrendingMovieRepository(
            movieService,
            apiKey,
            dataManager,
            networkStateManager
        )
    }

    @HomeScope
    @Provides
    fun providePopularRepository(
        movieService: MovieService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): PopularMovieRepository {
        return PopularMovieRepository(
            movieService,
            apiKey,
            dataManager,
            networkStateManager
        )
    }

    @HomeScope
    @Provides
    fun provideTopRatedRepository(
        movieService: MovieService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): TopRatedMovieRepository {
        return TopRatedMovieRepository(
            movieService,
            apiKey,
            dataManager,
            networkStateManager
        )
    }

    @HomeScope
    @Provides
    fun provideUpcomingRepository(
        movieService: MovieService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): UpcomingMovieRepository {
        return UpcomingMovieRepository(
            movieService,
            apiKey,
            dataManager,
            networkStateManager
        )
    }

    @HomeScope
    @Provides
    fun provideNowPlayingRepository(
        movieService: MovieService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): NowPlayingRepository {
        return NowPlayingRepository(
            movieService,
            apiKey,
            dataManager,
            networkStateManager
        )
    }
}