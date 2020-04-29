package com.example.slushflicks.di.home.movie

import com.example.slushflicks.ui.home.movie.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieFragmentBuilderModule {

    @MovieListScope
    @ContributesAndroidInjector
    internal abstract fun bindTrendingMovieFragment(): TrendingFragment

    @MovieListScope
    @ContributesAndroidInjector
    internal abstract fun bindPopularMovieFragment(): PopularFragment

    @MovieListScope
    @ContributesAndroidInjector
    internal abstract fun bindTopRatedMovieFragment(): TopRatedFragment

    @MovieListScope
    @ContributesAndroidInjector
    internal abstract fun bindUpcomingMovieFragment(): UpcomingFragment

    @MovieListScope
    @ContributesAndroidInjector
    internal abstract fun bindNowPlayingMovieFragment(): NowPlayingFragment

}