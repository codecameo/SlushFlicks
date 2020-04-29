package com.sifat.slushflicks.ui.state

/**
 * This state model is returned from repository
 * and repository only provides either Success or Error response
 * */
sealed class DataState<Data> {
    class Success<Data>(val dataResponse : DataSuccessResponse<Data>) : DataState<Data>()
    class Error<Data>(val dataResponse : DataErrorResponse<Data>) : DataState<Data>()
}