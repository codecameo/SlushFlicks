package com.example.slushflicks.api

sealed class ApiResponse<Data>
data class ApiErrorResponse<Data>(val statusCode : Int, val apiTag : String?, val errorMessage : String?) : ApiResponse<Data>()
data class ApiSuccessResponse<Data>(val data : Data? = null, val message : String? = null) : ApiResponse<Data>()
class ApiEmptyResponse<Data> : ApiResponse<Data>()