package com.sifat.slushflicks.di.home.movie

import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.di.constant.*
import com.sifat.slushflicks.repository.movie.MovieHomeRepository
import com.sifat.slushflicks.repository.movie.MovieListRepository
import com.sifat.slushflicks.repository.movie.impl.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = [InnerModule::class])
abstract class MovieModule {

    @MovieScope
    @Binds
    abstract fun bindMovieHomeRepository(movieHomeRepositoryImpl: MovieHomeRepositoryImpl): MovieHomeRepository

    @MovieScope
    @Binds
    @Named(NAME_TRENDING_MOVIE_REPO)
    abstract fun provideTrendingMovieRepository(
        trendingMovieRepositoryImpl: TrendingMovieRepositoryImpl
    ): MovieListRepository

    @MovieScope
    @Binds
    @Named(NAME_POPULAR_MOVIE_REPO)
    abstract fun providePopularRepository(
        popularMovieRepositoryImpl: PopularMovieRepositoryImpl
    ): MovieListRepository

    @MovieScope
    @Binds
    @Named(NAME_TOP_RATED_MOVIE_REPO)
    abstract fun provideTopRatedRepository(
        topRatedMovieRepositoryImpl: TopRatedMovieRepositoryImpl
    ): MovieListRepository

    @MovieScope
    @Binds
    @Named(NAME_UPCOMING_MOVIE_REPO)
    abstract fun provideUpcomingRepository(
        upcomingMovieRepositoryImpl: UpcomingMovieRepositoryImpl
    ): MovieListRepository

    @MovieScope
    @Binds
    @Named(NAME_NOW_PLAYING_MOVIE_REPO)
    abstract fun provideNowPlayingRepository(
        nowPlayingRepositoryImpl: NowPlayingRepositoryImpl
    ): MovieListRepository
}

@Module
class InnerModule {

    @MovieScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}