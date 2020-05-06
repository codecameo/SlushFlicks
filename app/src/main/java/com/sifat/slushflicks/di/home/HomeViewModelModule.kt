package com.sifat.slushflicks.di.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
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
}