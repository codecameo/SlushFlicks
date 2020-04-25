package com.example.slushflicks.ui.state

import com.example.slushflicks.api.ApiError
import com.example.slushflicks.utils.EMPTY_STRING

sealed class ViewState {
    class Loading<Data>(val data : Data?) : ViewState()
    class Success<Data>(val data : Data?, val message : String = EMPTY_STRING) : ViewState()
    class Error(val errorMessage : String, val errorAction : ErrorAction = ErrorAction.TOAST) : ViewState()
}

enum class ErrorAction {
    TOAST,
    SNACKBAR,
    WARNING_DIALOG,
    TERMINATION_DIALOG
}