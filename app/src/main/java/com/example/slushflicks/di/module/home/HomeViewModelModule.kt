package com.example.slushflicks.di.module.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slushflicks.di.ViewModelKey
import com.example.slushflicks.di.scope.HomeScope
import com.example.slushflicks.ui.home.viewmodel.HomeViewModel
import com.example.slushflicks.ui.viewmodel.HomeViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeViewModelModule {

    @HomeScope
    @Binds
    abstract fun getFactory(homeViewModelFactory: HomeViewModelFactory) : ViewModelProvider.Factory

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun getViewModel(homeViewModel: HomeViewModel) : ViewModel

}