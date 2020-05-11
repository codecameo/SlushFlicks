package com.sifat.slushflicks.di.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.constant.NAME_CONTENT_FACTORY
import com.sifat.slushflicks.ui.home.movie.viewmodel.MovieViewModel
import com.sifat.slushflicks.ui.viewmodel.ContentViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class MovieViewModelModule {

    @MovieScope
    @Binds
    @Named(NAME_CONTENT_FACTORY)
    abstract fun bindFactory(contentViewModelFactory: ContentViewModelFactory): ViewModelProvider.Factory

    @MovieScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel
}