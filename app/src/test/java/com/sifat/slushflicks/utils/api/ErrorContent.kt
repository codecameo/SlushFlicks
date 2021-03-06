package com.sifat.slushflicks.utils.api

import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiErrorResponse
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ErrorResponse
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED

const val unauthResponse =
    """
    {
      "status_code": 7,
      "status_message": "Invalid API key: You must be granted a valid key.",
      "success": false
    }
    """
const val noResourceFound = """
    {
      "status_code": 34,
      "status_message": "The resource you requested could not be found."
    }
"""

fun <T> getUnAuthResponse(gson: Gson, tag: String): ApiResponse<T>? {
    val credit = gson.fromJson(unauthResponse, ErrorResponse::class.java)
    return ApiErrorResponse<T>(
        statusCode = UNAUTHORIZED,
        errorMessage = credit.message,
        apiTag = tag
    )
}

fun <T> getNoResResponse(gson: Gson, tag: String): ApiResponse<T>? {
    val credit = gson.fromJson(noResourceFound, ErrorResponse::class.java)
    return ApiErrorResponse<T>(
        statusCode = RESOURCE_NOT_FOUND,
        errorMessage = credit.message,
        apiTag = tag
    )
}