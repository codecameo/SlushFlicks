package com.example.slushflicks.ui.state

import com.example.slushflicks.api.ApiError
import com.example.slushflicks.utils.EMPTY_STRING

sealed class DataState<Data> {
    class Success<Data>(val data : Data?, val message : String = EMPTY_STRING) : DataState<Data>()
    class Error<Data>(val apiError : ApiError) : DataState<Data>()
}