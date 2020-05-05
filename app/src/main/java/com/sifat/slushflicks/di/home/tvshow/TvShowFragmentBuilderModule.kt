package com.sifat.slushflicks.di.home.tvshow

import com.sifat.slushflicks.ui.home.tvshow.fragment.AirTodayTvFragment
import com.sifat.slushflicks.ui.home.tvshow.fragment.PopularTvFragment
import com.sifat.slushflicks.ui.home.tvshow.fragment.TopRatedTvFragment
import com.sifat.slushflicks.ui.home.tvshow.fragment.TrendingTvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TvShowFragmentBuilderModule {

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindTrendingTvFragment(): TrendingTvFragment

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindPopularTvFragment(): PopularTvFragment

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindTopRatedTvFragment(): TopRatedTvFragment

    @TvShowListScope
    @ContributesAndroidInjector
    internal abstract fun bindAirTodayTvFragment(): AirTodayTvFragment
}