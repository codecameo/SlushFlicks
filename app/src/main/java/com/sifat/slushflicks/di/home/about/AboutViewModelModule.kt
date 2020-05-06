package com.sifat.slushflicks.di.home.about

import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.ui.home.about.AboutViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AboutViewModelModule {

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(aboutViewModel: AboutViewModel): ViewModel
}