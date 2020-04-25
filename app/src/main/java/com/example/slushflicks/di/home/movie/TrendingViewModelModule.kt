package com.example.slushflicks.di.home.movie

import androidx.lifecycle.ViewModel
import com.example.slushflicks.di.ViewModelKey
import com.example.slushflicks.ui.home.movie.viewmodel.MovieViewModel
import com.example.slushflicks.ui.home.movie.viewmodel.TrendingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TrendingViewModelModule {

    /*@MovieListScope
    @Binds
    @IntoMap
    @ViewModelKey(TrendingViewModel::class)
    abstract fun getViewModel(trendingViewModel: TrendingViewModel): ViewModel*/

}