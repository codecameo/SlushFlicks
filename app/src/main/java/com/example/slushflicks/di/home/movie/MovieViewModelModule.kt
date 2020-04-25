package com.example.slushflicks.di.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slushflicks.di.ViewModelKey
import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.ui.home.movie.viewmodel.MovieViewModel
import com.example.slushflicks.ui.home.viewmodel.HomeViewModel
import com.example.slushflicks.ui.viewmodel.HomeViewModelFactory
import com.example.slushflicks.ui.viewmodel.MovieViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MovieViewModelModule {

    /*@MovieScope
    @Binds
    abstract fun getFactory(movieViewModelFactory: MovieViewModelFactory) : ViewModelProvider.Factory*/

    /*@MovieScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun getMovieViewModel(movieViewModel: MovieViewModel) : ViewModel*/
}