package com.sifat.slushflicks.di.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.ui.home.about.AboutViewModel
import com.sifat.slushflicks.ui.home.movie.viewmodel.*
import com.sifat.slushflicks.ui.home.search.SearchViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TrendingTvViewModel
import com.sifat.slushflicks.ui.home.tvshow.viewmodel.TvShowViewModel
import com.sifat.slushflicks.ui.home.viewmodel.HomeViewModel
import com.sifat.slushflicks.ui.viewmodel.HomeViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @HomeScope
    @Binds
    abstract fun bindFactory(homeViewModelFactory: HomeViewModelFactory): ViewModelProvider.Factory

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(TrendingViewModel::class)
    abstract fun bindTrendingViewModel(trendingViewModel: TrendingViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindPopularViewModel(popularViewModel: PopularViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewModel::class)
    abstract fun bindTopRatedViewModel(topRatedViewModel: TopRatedViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(NowPlayingViewModel::class)
    abstract fun bindNowPlayingViewModel(nowPlayingViewModel: NowPlayingViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(UpcomingViewModel::class)
    abstract fun bindUpcomingViewModel(upcomingViewModel: UpcomingViewModel): ViewModel

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
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(aboutViewModel: AboutViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel


}