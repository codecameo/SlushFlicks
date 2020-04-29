package com.sifat.slushflicks.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.di.home.HomeScope

import javax.inject.Inject
import javax.inject.Provider

@HomeScope
open class HomeViewModelFactory @Inject
constructor(
    creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators)