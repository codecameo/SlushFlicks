package com.sifat.slushflicks.di.home.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.constant.NAME_CONTENT_FACTORY
import com.sifat.slushflicks.ui.home.about.AboutViewModel
import com.sifat.slushflicks.ui.viewmodel.ContentViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class AboutViewModelModule {

    @AboutScope
    @Binds
    @Named(NAME_CONTENT_FACTORY)
    abstract fun bindFactory(contentViewModelFactory: ContentViewModelFactory): ViewModelProvider.Factory

    @AboutScope
    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(aboutViewModel: AboutViewModel): ViewModel
}