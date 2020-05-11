package com.sifat.slushflicks.di.home.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.constant.NAME_SHOW_LIST_FACTORY
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.AirTodayTvViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.PopularTvViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TopRatedTvViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TrendingTvViewModel
import com.sifat.slushflicks.ui.viewmodel.ListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class TvListViewModelModule {

    @TvShowListScope
    @Binds
    @Named(NAME_SHOW_LIST_FACTORY)
    abstract fun bindFactory(listViewModelFactory: ListViewModelFactory): ViewModelProvider.Factory

    @TvShowListScope
    @Binds
    @IntoMap
    @ViewModelKey(TrendingTvViewModel::class)
    abstract fun bindTrendingTvViewModel(trendingTvViewModel: TrendingTvViewModel): ViewModel

    @TvShowListScope
    @Binds
    @IntoMap
    @ViewModelKey(PopularTvViewModel::class)
    abstract fun bindPopularTvViewModel(popularTvViewModel: PopularTvViewModel): ViewModel

    @TvShowListScope
    @Binds
    @IntoMap
    @ViewModelKey(AirTodayTvViewModel::class)
    abstract fun bindAirTodayTvViewModel(airTodayTvViewModel: AirTodayTvViewModel): ViewModel

    @TvShowListScope
    @Binds
    @IntoMap
    @ViewModelKey(TopRatedTvViewModel::class)
    abstract fun bindTopRatedTvViewModel(topRatedTvViewModel: TopRatedTvViewModel): ViewModel
}