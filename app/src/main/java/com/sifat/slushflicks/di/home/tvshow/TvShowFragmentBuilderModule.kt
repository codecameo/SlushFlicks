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
    @ContributesAndroidInjector(modules = [TvListModule::class, TvListViewModelModule::class])
    internal abstract fun bindTrendingTvFragment(): TrendingTvFragment

    @TvShowListScope
    @ContributesAndroidInjector(modules = [TvListModule::class, TvListViewModelModule::class])
    internal abstract fun bindPopularTvFragment(): PopularTvFragment

    @TvShowListScope
    @ContributesAndroidInjector(modules = [TvListModule::class, TvListViewModelModule::class])
    internal abstract fun bindTopRatedTvFragment(): TopRatedTvFragment

    @TvShowListScope
    @ContributesAndroidInjector(modules = [TvListModule::class, TvListViewModelModule::class])
    internal abstract fun bindAirTodayTvFragment(): AirTodayTvFragment
}