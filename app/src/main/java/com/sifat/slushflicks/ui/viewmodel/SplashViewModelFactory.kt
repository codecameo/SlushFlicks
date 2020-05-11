package com.sifat.slushflicks.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.di.splash.SplashScope
import javax.inject.Inject
import javax.inject.Provider

@SplashScope
class SplashViewModelFactory @Inject
constructor(
    creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators)