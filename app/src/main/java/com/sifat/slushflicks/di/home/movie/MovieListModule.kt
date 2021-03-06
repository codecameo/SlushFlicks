package com.sifat.slushflicks.di.home.movie

import com.sifat.slushflicks.di.constant.*
import com.sifat.slushflicks.repository.movie.MovieListRepository
import com.sifat.slushflicks.repository.movie.impl.*
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class MovieListModule {

    @MovieListScope
    @Binds
    @Named(NAME_TRENDING_MOVIE_REPO)
    abstract fun provideTrendingMovieRepository(
        trendingMovieRepositoryImpl: TrendingMovieRepositoryImpl
    ): MovieListRepository

    @MovieListScope
    @Binds
    @Named(NAME_POPULAR_MOVIE_REPO)
    abstract fun providePopularRepository(
        popularMovieRepositoryImpl: PopularMovieRepositoryImpl
    ): MovieListRepository

    @MovieListScope
    @Binds
    @Named(NAME_TOP_RATED_MOVIE_REPO)
    abstract fun provideTopRatedRepository(
        topRatedMovieRepositoryImpl: TopRatedMovieRepositoryImpl
    ): MovieListRepository

    @MovieListScope
    @Binds
    @Named(NAME_UPCOMING_MOVIE_REPO)
    abstract fun provideUpcomingRepository(
        upcomingMovieRepositoryImpl: UpcomingMovieRepositoryImpl
    ): MovieListRepository

    @MovieListScope
    @Binds
    @Named(NAME_NOW_PLAYING_MOVIE_REPO)
    abstract fun provideNowPlayingRepository(
        nowPlayingRepositoryImpl: NowPlayingRepositoryImpl
    ): MovieListRepository
}