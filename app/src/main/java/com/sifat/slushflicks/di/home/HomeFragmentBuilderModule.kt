package com.sifat.slushflicks.di.home

import com.sifat.slushflicks.di.home.about.AboutScope
import com.sifat.slushflicks.di.home.about.AboutViewModelModule
import com.sifat.slushflicks.di.home.movie.MovieFragmentBuilderModule
import com.sifat.slushflicks.di.home.movie.MovieModule
import com.sifat.slushflicks.di.home.movie.MovieScope
import com.sifat.slushflicks.di.home.movie.MovieViewModelModule
import com.sifat.slushflicks.di.home.search.SearchModule
import com.sifat.slushflicks.di.home.search.SearchScope
import com.sifat.slushflicks.di.home.search.SearchViewModelModule
import com.sifat.slushflicks.di.home.tvshow.TvModule
import com.sifat.slushflicks.di.home.tvshow.TvShowFragmentBuilderModule
import com.sifat.slushflicks.di.home.tvshow.TvShowScope
import com.sifat.slushflicks.di.home.tvshow.TvViewModelModule
import com.sifat.slushflicks.ui.home.about.AboutFragment
import com.sifat.slushflicks.ui.home.movie.fragment.HomeMovieFragment
import com.sifat.slushflicks.ui.home.search.fragment.SearchFragment
import com.sifat.slushflicks.ui.home.tvshow.fragment.HomeTvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilderModule {

    @MovieScope
    @ContributesAndroidInjector(
        modules = [MovieFragmentBuilderModule::class,
            MovieViewModelModule::class,
            MovieModule::class]
    )
    internal abstract fun bindMovieListFragment(): HomeMovieFragment

    @TvShowScope
    @ContributesAndroidInjector(
        modules = [TvShowFragmentBuilderModule::class,
            TvViewModelModule::class,
            TvModule::class]
    )
    internal abstract fun bindTvShowListFragment(): HomeTvFragment

    @SearchScope
    @ContributesAndroidInjector(
        modules = [SearchModule::class,
            SearchViewModelModule::class]
    )
    internal abstract fun bindSearchFragment(): SearchFragment

    @AboutScope
    @ContributesAndroidInjector(
        modules = [
            AboutViewModelModule::class]
    )
    internal abstract fun bindAboutFragment(): AboutFragment
}