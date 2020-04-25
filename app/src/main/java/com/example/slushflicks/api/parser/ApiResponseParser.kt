package com.example.slushflicks.api.parser

import android.util.Log
import com.example.slushflicks.api.ApiEmptyModel
import com.example.slushflicks.api.ApiErrorModel
import com.example.slushflicks.api.ApiResponseModel
import com.example.slushflicks.api.ApiSuccessModel
import com.google.gson.Gson
import retrofit2.Response

/**
 * This class converts api data into a form what app can use to make relevant action
 * */
class ApiResponseParser {

    companion object {
        const val TAG = "ApiResponseParser"

        fun <Data> create(statusCode : Int, apiTag : String?, response: Response<Data>, gson: Gson) : ApiResponseModel<Data> {
            Log.d(TAG, "ApiResponseParser: response: ${response}")
            Log.d(TAG, "ApiResponseParser: raw: ${response.raw()}")
            Log.d(TAG, "ApiResponseParser: headers: ${response.headers()}")
            Log.d(TAG, "ApiResponseParser: message: ${response.message()}")

            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyModel<Data>()
                } else {
                    ApiSuccessModel<Data>(
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

        fun <Data> create(statusCode : Int, apiTag : String, error: Throwable): ApiResponseModel<Data> {
            return ApiErrorModel<Data>(
                statusCode = statusCode,
                apiTag = apiTag,
                errorMessage = error.message
            )
        }
    }
}