package com.example.slushflicks.api

import com.example.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR

sealed class ApiResponse<Data>

data class ApiErrorResponse<Data>(
    val statusCode: Int = INTERNAL_ERROR,
    val apiTag: String? = null,
    val errorMessage: String? = null
) : ApiResponse<Data>()

data class ApiSuccessResponse<Data>(
    val data: Data? = null,
    val message: String? = null
) : ApiResponse<Data>()

class ApiEmptyResponse<Data>(
    val statusCode: Int = INTERNAL_ERROR,
    val apiTag: String?
) : ApiResponse<Data>()