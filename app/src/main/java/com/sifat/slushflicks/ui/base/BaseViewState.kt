package com.sifat.slushflicks.ui.base

import androidx.lifecycle.MutableLiveData

abstract class BaseViewState<ViewAction> {
    private val _action: MutableLiveData<ViewAction> = MutableLiveData<ViewAction>()
    fun getAction(): MutableLiveData<ViewAction> = _action
}