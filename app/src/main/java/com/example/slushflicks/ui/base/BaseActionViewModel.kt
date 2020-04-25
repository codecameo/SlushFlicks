package com.example.slushflicks.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.slushflicks.ui.home.movie.state.MovieListDataAction
import com.example.slushflicks.ui.home.movie.state.MovieListViewAction
import com.example.slushflicks.ui.home.movie.state.viewstate.MovieListViewState

abstract class BaseActionViewModel<DataAction, ViewAction, ViewState : BaseViewState<ViewAction>> :
    ViewModel() {
    private val dataState = MediatorLiveData<DataAction>()
    protected abstract var viewState: ViewState
    protected fun getAction() = viewState.getAction()

    fun observeViewAction(): LiveData<ViewAction> = viewState.getAction()
    fun observeDataAction(): LiveData<DataAction> = dataState
}