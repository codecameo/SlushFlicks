package com.sifat.slushflicks.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.di.details.DetailsScope
import javax.inject.Inject
import javax.inject.Provider

@DetailsScope
open class DetailsViewModelFactory @Inject
constructor(
    creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : BaseViewModelFactory(creators)