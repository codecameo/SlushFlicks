package com.example.slushflicks.ui.base

import androidx.lifecycle.MutableLiveData
import com.example.slushflicks.ui.home.movie.state.MovieListViewAction

abstract class BaseViewState<ViewAction> {
    private val _action: MutableLiveData<ViewAction> = MutableLiveData<ViewAction>()
    fun getAction() : MutableLiveData<ViewAction> = _action
}