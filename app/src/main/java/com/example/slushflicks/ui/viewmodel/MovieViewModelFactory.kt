package com.example.slushflicks.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.di.home.movie.MovieScope
import javax.inject.Inject
import javax.inject.Provider

@MovieScope
open class MovieViewModelFactory @Inject
constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators)