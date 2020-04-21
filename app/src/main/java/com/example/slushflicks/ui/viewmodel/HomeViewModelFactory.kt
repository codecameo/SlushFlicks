package com.example.slushflicks.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.slushflicks.di.scope.HomeScope

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@HomeScope
open class HomeViewModelFactory @Inject
constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators) {


}