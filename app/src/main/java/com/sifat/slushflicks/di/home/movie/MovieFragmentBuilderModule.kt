package com.sifat.slushflicks.di.home.movie

import com.sifat.slushflicks.ui.home.movie.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieFragmentBuilderModule {

    @MovieListScope
    @ContributesAndroidInjector(modules = [MovieListModule::class, MovieListViewModelModule::class])
    internal abstract fun bindTrendingMovieFragment(): TrendingFragment

    @MovieListScope
    @ContributesAndroidInjector(modules = [MovieListModule::class, MovieListViewModelModule::class])
    internal abstract fun bindPopularMovieFragment(): PopularFragment

    @MovieListScope
    @ContributesAndroidInjector(modules = [MovieListModule::class, MovieListViewModelModule::class])
    internal abstract fun bindTopRatedMovieFragment(): TopRatedFragment

    @MovieListScope
    @ContributesAndroidInjector(modules = [MovieListModule::class, MovieListViewModelModule::class])
    internal abstract fun bindUpcomingMovieFragment(): UpcomingFragment

    @MovieListScope
    @ContributesAndroidInjector(modules = [MovieListModule::class, MovieListViewModelModule::class])
    internal abstract fun bindNowPlayingMovieFragment(): NowPlayingFragment
}