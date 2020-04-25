package com.example.slushflicks.di.home.movie

import com.example.slushflicks.ui.home.movie.fragment.TrendingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieFragmentBuilderModule {

    @MovieListScope
    @ContributesAndroidInjector(modules = [TrendingViewModelModule::class])
    internal abstract fun bindTrendingMovieFragment(): TrendingFragment

}