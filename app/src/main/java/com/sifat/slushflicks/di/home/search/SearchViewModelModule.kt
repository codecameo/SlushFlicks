package com.sifat.slushflicks.di.home.search

import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.di.ViewModelKey
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.ui.home.search.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {

    @HomeScope
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}