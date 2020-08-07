package com.sifat.slushflicks.api.parser

import android.util.Log
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiErrorResponse
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.ErrorResponse
import com.sifat.slushflicks.api.StatusCode

/**
 * Provide a mechanism to customize error parsing based on Api Endpoint and Status Code based on ApiTags
 * In case an api endpoint provides with different response for different status code
 * [ApiResponseParser] will use this parser to parse error responses
 * */
class ApiErrorParser<Data>(private val gson: Gson) {

    val TAG = "ApiErrorParser"

    fun getApiErrorResponse(statusCode: Int, apiTag: String?, errorResponse : String?): ApiErrorResponse<Data> {
        Log.d(TAG, "$errorResponse")
        val errorMessage : String? = errorResponse?.let {
            when(apiTag) {
                ApiTag.TRENDING_MOVIE_API_TAG -> {
                   when(statusCode) {
                       StatusCode.RESOURCE_NOT_FOUND -> {
                           getCommonErrorMessage(errorResponse)
                       }
                       StatusCode.UNAUTHORIZED -> {
                           getCommonErrorMessage(errorResponse)
                       }
                       else -> null
                   }
                }
                else -> {
                    when (statusCode) {
                        StatusCode.RESOURCE_NOT_FOUND -> {
                            getCommonErrorMessage(errorResponse)
                        }
                        StatusCode.UNAUTHORIZED -> {
                            getCommonErrorMessage(errorResponse)
                        }
                        else -> null
                    }
                }
            }
        }

        return ApiErrorResponse(
            statusCode = statusCode,
            apiTag = apiTag,
            errorMessage = errorMessage)
    }

    private fun getCommonErrorMessage(errorResponse: String): String {
        val error = gson.fromJson(errorResponse, ErrorResponse::class.java)
        return error.message
    }
}