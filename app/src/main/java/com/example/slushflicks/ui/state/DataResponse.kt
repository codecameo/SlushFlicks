package com.example.slushflicks.ui.state

sealed class DataResponse<Data>

data class DataSuccessResponse<Data>(
    val data: Data? = null,
    val metaData: MetaData? = null,
    val message: String? = null
) : DataResponse<Data>()

data class DataErrorResponse<Data>(
    val statusCode: Int,
    val apiTag: String?,
    val errorMessage: String?
) : DataResponse<Data>()

data class MetaData(
    val page: Int,
    val totalResult: Long,
    val totalPage: Int
)