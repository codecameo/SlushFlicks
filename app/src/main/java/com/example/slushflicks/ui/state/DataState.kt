package com.example.slushflicks.ui.state

import com.example.slushflicks.api.ApiErrorModel
import com.example.slushflicks.api.ApiSuccessModel
import com.example.slushflicks.utils.EMPTY_STRING

/**
 * This state model is returned from repository
 * and repository only provides either Success or Error response
 * */
sealed class DataState<Data> {
    class Success<Data>(val apiSuccess : ApiSuccessModel<Data>) : DataState<Data>()
    class Error<Data>(val apiError : ApiErrorModel<Data>) : DataState<Data>()
}