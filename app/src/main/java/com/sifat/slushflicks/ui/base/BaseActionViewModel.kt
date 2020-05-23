package com.sifat.slushflicks.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.sifat.slushflicks.ui.state.DataState

abstract class BaseActionViewModel<ViewAction, ViewState : BaseViewState<ViewAction>> :
    ViewModel() {
    protected val dataState = MediatorLiveData<DataState<Any>>()
    protected abstract val viewState: ViewState
    protected fun getAction() = viewState.getAction()

    fun observeViewAction(): LiveData<ViewAction> = viewState.getAction()
    fun observeDataAction(): LiveData<DataState<Any>> = dataState
}