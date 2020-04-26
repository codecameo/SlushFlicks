package com.example.slushflicks.di.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slushflicks.di.ViewModelKey
import com.example.slushflicks.di.home.movie.MovieListScope
import com.example.slushflicks.di.home.movie.MovieScope
import com.example.slushflicks.ui.home.movie.viewmodel.MovieViewModel
import com.example.slushflicks.ui.home.movie.viewmodel.TrendingViewModel
import com.example.slushflicks.ui.home.viewmodel.HomeViewModel
import com.example.slushflicks.ui.viewmodel.HomeViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @HomeScope
    @Binds
    abstract fun getFactory(homeViewModelFactory: HomeViewModelFactory): ViewModelProvider.Factory

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun getHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(TrendingViewModel::class)
    abstract fun getTrendingViewModel(trendingViewModel: TrendingViewModel): ViewModel

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun getMovieViewModel(movieViewModel: MovieViewModel): ViewModel

}