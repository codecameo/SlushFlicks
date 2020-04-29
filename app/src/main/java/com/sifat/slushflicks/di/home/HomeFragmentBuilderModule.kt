package com.sifat.slushflicks.di.home

import com.sifat.slushflicks.di.home.movie.MovieFragmentBuilderModule
import com.sifat.slushflicks.di.home.movie.MovieScope
import com.sifat.slushflicks.di.home.movie.MovieViewModelModule
import com.sifat.slushflicks.ui.home.movie.fragment.HomeMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilderModule {

    @MovieScope
    @ContributesAndroidInjector(modules = [MovieFragmentBuilderModule::class, MovieViewModelModule::class])
    internal abstract fun bindMovieListFragment(): HomeMovieFragment
}