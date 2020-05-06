package com.sifat.slushflicks.di.home

import com.sifat.slushflicks.di.home.about.AboutScope
import com.sifat.slushflicks.di.home.movie.MovieFragmentBuilderModule
import com.sifat.slushflicks.di.home.movie.MovieScope
import com.sifat.slushflicks.di.home.search.SearchScope
import com.sifat.slushflicks.di.home.tvshow.TvShowFragmentBuilderModule
import com.sifat.slushflicks.di.home.tvshow.TvShowScope
import com.sifat.slushflicks.ui.home.about.AboutFragment
import com.sifat.slushflicks.ui.home.movie.fragment.HomeMovieFragment
import com.sifat.slushflicks.ui.home.search.SearchFragment
import com.sifat.slushflicks.ui.home.tvshow.fragment.HomeTvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilderModule {

    @MovieScope
    @ContributesAndroidInjector(modules = [MovieFragmentBuilderModule::class])
    internal abstract fun bindMovieListFragment(): HomeMovieFragment

    @TvShowScope
    @ContributesAndroidInjector(modules = [TvShowFragmentBuilderModule::class])
    internal abstract fun bindTvShowListFragment(): HomeTvFragment

    @SearchScope
    @ContributesAndroidInjector
    internal abstract fun bindSearchFragment(): SearchFragment

    @AboutScope
    @ContributesAndroidInjector
    internal abstract fun bindAboutFragment(): AboutFragment
}