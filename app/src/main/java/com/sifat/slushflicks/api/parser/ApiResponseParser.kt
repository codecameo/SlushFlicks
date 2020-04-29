package com.sifat.slushflicks.api.parser

import android.util.Log
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiEmptyResponse
import com.sifat.slushflicks.api.ApiErrorResponse
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import retrofit2.Response

/**
 * This class converts api data into a form what app can use to make relevant action
 * */
class ApiResponseParser {

    companion object {
        const val TAG = "ApiResponseParser"

        fun <Data> create(statusCode : Int, apiTag : String?, response: Response<Data>, gson: Gson) : ApiResponse<Data> {
            Log.d(TAG, "ApiResponseParser: response: ${response}")
            Log.d(TAG, "ApiResponseParser: raw: ${response.raw()}")
            Log.d(TAG, "ApiResponseParser: headers: ${response.headers()}")
            Log.d(TAG, "ApiResponseParser: message: ${response.message()}")

            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse<Data>(
                        apiTag = apiTag,
                        statusCode = statusCode
                    )
                } else {
                    ApiSuccessResponse<Data>(
                        data = body
                    )
                }
            } else {
                ApiErrorParser<Data>(gson = gson).getApiErrorResponse(
                    statusCode = statusCode,
                    apiTag = apiTag,
                    errorResponse = response.errorBody()?.string())
            }
        }

        fun <Data> create(statusCode : Int, apiTag : String?, error: Throwable): ApiResponse<Data> {
            return ApiErrorResponse<Data>(
                statusCode = statusCode,
                apiTag = apiTag,
                errorMessage = error.message
            )
        }
    }
}