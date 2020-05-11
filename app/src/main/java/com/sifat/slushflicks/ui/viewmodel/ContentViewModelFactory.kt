package com.sifat.slushflicks.ui.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

class ContentViewModelFactory @Inject
constructor(
    creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators)