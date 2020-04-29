package com.sifat.slushflicks.ui.state

import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR

sealed class DataResponse<Data>

data class DataSuccessResponse<Data>(
    val data: Data? = null,
    val metaData: MetaData? = null,
    val message: String? = null
) : DataResponse<Data>()

data class DataErrorResponse<Data>(
    val statusCode: Int = INTERNAL_ERROR,
    val apiTag: String? = null,
    val errorMessage: String? = null
) : DataResponse<Data>()

data class MetaData(
    val page: Int,
    val totalResult: Long,
    val totalPage: Int
)