package com.example.slushflicks.ui.base

abstract class ListModel<Data>(val data : Data? = null, val state: ListViewState) {
    abstract fun getViewType() : Int
}

enum class ListViewState {
    LOADING,
    VIEW
}