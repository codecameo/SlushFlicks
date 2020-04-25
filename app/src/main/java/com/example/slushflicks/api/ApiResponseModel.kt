package com.example.slushflicks.api

sealed class ApiResponseModel<Data>
data class ApiErrorModel<Data>(val statusCode : Int, val apiTag : String?, val errorMessage : String?) : ApiResponseModel<Data>()
data class ApiSuccessModel<Data>(val data : Data? = null, val metaData: MetaData? = null, val message : String? = null) : ApiResponseModel<Data>()
class ApiEmptyModel<Data> : ApiResponseModel<Data>()
data class MetaData(val page : Int, val totalResult : Long, val totalPage : Int)