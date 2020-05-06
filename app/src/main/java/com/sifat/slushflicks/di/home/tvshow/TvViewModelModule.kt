package com.sifat.slushflicks.di.home.tvshow

import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TvViewModelModule {

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun bindTvShowViewModel(tvShowViewModel: TvShowViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(TrendingTvViewModel::class)
    abstract fun bindTrendingTvViewModel(trendingTvViewModel: TrendingTvViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(PopularTvViewModel::class)
    abstract fun bindPopularTvViewModel(popularTvViewModel: PopularTvViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(AirTodayTvViewModel::class)
    abstract fun bindAirTodayTvViewModel(airTodayTvViewModel: AirTodayTvViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(TopRatedTvViewModel::class)
    abstract fun bindTopRatedTvViewModel(topRatedTvViewModel: TopRatedTvViewModel): ViewModel
}