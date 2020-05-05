package com.sifat.slushflicks.di.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.ui.details.viewmodel.MovieDetailsViewModel
import com.sifat.slushflicks.ui.viewmodel.DetailsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsViewModelModule {

    @DetailsScope
    @Binds
    abstract fun bindFactory(detailsViewModelFactory: DetailsViewModelFactory): ViewModelProvider.Factory

    @DetailsScope
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}