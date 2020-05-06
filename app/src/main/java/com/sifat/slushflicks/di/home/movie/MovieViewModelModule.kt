package com.sifat.slushflicks.di.home.movie

import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.ui.home.movie.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieViewModelModule {

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
}