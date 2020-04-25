package com.example.slushflicks.api.parser

import android.util.Log
import com.example.slushflicks.api.ApiErrorModel
import com.example.slushflicks.api.ApiTag
import com.example.slushflicks.api.StatusCode
import com.google.gson.Gson

/**
 * Provide a mechanism to customize error parsing based on Api Endpoint and Status Code based on ApiTags
 * In case an api endpoint provides with different response for different status code
 * [ApiResponse] will use this parser to parse error responses
 * */
class ApiErrorParser<Data>(private val gson: Gson) {

    val TAG = "ApiErrorParser"

    fun getApiErrorResponse(statusCode: Int, apiTag: String?, errorResponse : String?): ApiErrorModel<Data> {
        Log.d(TAG, "$errorResponse")
        val errorMessage : String? = errorResponse?.let {
            when(apiTag) {
                ApiTag.TRENDING_API_TAG -> {
                   when(statusCode) {
                       StatusCode.RESOURCE_NOT_FOUND -> {
                           ""
                       }
                       StatusCode.UNAUTHORIZED -> {
                           ""
                       }
                       else -> null
                   }
                }
                else -> null
            }
        }

        return ApiErrorModel(
            statusCode = statusCode,
            apiTag = apiTag,
            errorMessage = errorMessage)
    }


}