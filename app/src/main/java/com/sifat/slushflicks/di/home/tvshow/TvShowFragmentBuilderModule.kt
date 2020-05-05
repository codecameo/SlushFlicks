package com.sifat.slushflicks.di.home.tvshow

import com.sifat.slushflicks.ui.home.tvshow.fragment.TrendingTvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TvShowFragmentBuilderModule {

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindTrendingTvFragment(): TrendingTvFragment

    /*@TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindPopularMovieFragment(): PopularFragment

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindTopRatedMovieFragment(): TopRatedFragment

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindUpcomingMovieFragment(): UpcomingFragment

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindNowPlayingMovieFragment(): NowPlayingFragment*/

}