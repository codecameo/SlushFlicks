package com.sifat.slushflicks.di.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.constant.NAME_SHOW_LIST_FACTORY
import com.sifat.slushflicks.ui.home.movie.viewmodel.*
import com.sifat.slushflicks.ui.viewmodel.ListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class MovieListViewModelModule {

    @MovieListScope
    @Binds
    @Named(NAME_SHOW_LIST_FACTORY)
    abstract fun bindFactory(listViewModelFactory: ListViewModelFactory): ViewModelProvider.Factory

    @MovieListScope
    @Binds
    @IntoMap
    @ViewModelKey(TrendingViewModel::class)
    abstract fun bindTrendingViewModel(trendingViewModel: TrendingViewModel): ViewModel

    @MovieListScope
    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindPopularViewModel(popularViewModel: PopularViewModel): ViewModel

    @MovieListScope
    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewModel::class)
    abstract fun bindTopRatedViewModel(topRatedViewModel: TopRatedViewModel): ViewModel

    @MovieListScope
    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    abstract fun bindNowPlayingViewModel(nowPlayingViewModel: NowPlayingViewModel): ViewModel

    @MovieListScope
    @Binds
    @IntoMap
    @ViewModelKey(UpcomingViewModel::class)
    abstract fun bindUpcomingViewModel(upcomingViewModel: UpcomingViewModel): ViewModel
}