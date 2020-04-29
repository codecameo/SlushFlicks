package com.sifat.slushflicks.di.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.ui.splash.viewmodel.SplashViewModel
import com.sifat.slushflicks.ui.viewmodel.SplashViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashViewModelModule {

    @SplashScope
    @Binds
    abstract fun bindFactory(splashViewModelFactory: SplashViewModelFactory) : ViewModelProvider.Factory

    @SplashScope
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel) : ViewModel
}