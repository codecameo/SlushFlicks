package com.example.slushflicks.ui.state

import com.example.slushflicks.utils.EMPTY_STRING

sealed class ViewState<Data> {
    class Loading<Data>(val data : Data?) : ViewState<Data>()
    class Success<Data>(val data : Data?, val message : String = EMPTY_STRING) : ViewState<Data>()
    class Error<Data>(val errorMessage : String, val errorAction : ErrorAction = ErrorAction.TOAST) : ViewState<Data>()
}

enum class ErrorAction {
    TOAST,
    SNACKBAR,
    WARNING_DIALOG,
    TERMINATION_DIALOG
}