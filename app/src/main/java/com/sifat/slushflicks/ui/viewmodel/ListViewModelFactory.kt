package com.sifat.slushflicks.ui.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider

class ListViewModelFactory @Inject
constructor(
    creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators)