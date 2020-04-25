package com.example.slushflicks.di.home

import com.example.slushflicks.di.home.movie.MovieFragmentBuilderModule
import com.example.slushflicks.di.home.movie.MovieScope
import com.example.slushflicks.di.home.movie.MovieViewModelModule
import com.example.slushflicks.ui.home.movie.fragment.HomeMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilderModule {

    @MovieScope
    @ContributesAndroidInjector(modules = [MovieFragmentBuilderModule::class, MovieViewModelModule::class])
    internal abstract fun bindMovieListFragment(): HomeMovieFragment
}