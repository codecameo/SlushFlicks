package com.sifat.slushflicks.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

abstract class BaseActionViewModel<DataAction, ViewAction, ViewState : BaseViewState<ViewAction>> :
    ViewModel() {
    protected val dataState = MediatorLiveData<DataAction>()
    protected abstract val viewState: ViewState
    protected fun getAction() = viewState.getAction()

    fun observeViewAction(): LiveData<ViewAction> = viewState.getAction()
    fun observeDataAction(): LiveData<DataAction> = dataState
}