package com.sifat.slushflicks.di.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.ui.details.viewmodel.TvDetailsViewModel
import com.sifat.slushflicks.ui.viewmodel.DetailsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TvDetailsViewModelModule {

    @TvDetailsScope
    @Binds
    abstract fun bindFactory(detailsViewModelFactory: DetailsViewModelFactory): ViewModelProvider.Factory

    @TvDetailsScope
    @Binds
    @IntoMap
    @ViewModelKey(TvDetailsViewModel::class)
    abstract fun bindTvDetailsViewModel(tvDetailsViewModel: TvDetailsViewModel): ViewModel
}