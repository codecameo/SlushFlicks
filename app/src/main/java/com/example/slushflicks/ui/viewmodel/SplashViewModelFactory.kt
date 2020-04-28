package com.example.slushflicks.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.di.home.movie.MovieScope
import com.example.slushflicks.di.splash.SplashScope
import javax.inject.Inject
import javax.inject.Provider

@SplashScope
open class SplashViewModelFactory @Inject
constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators)