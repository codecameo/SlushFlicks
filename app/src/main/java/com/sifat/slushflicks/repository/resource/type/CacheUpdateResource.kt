package com.sifat.slushflicks.repository.resource.type

import com.sifat.slushflicks.api.ApiErrorResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.repository.resource.NetworkBoundResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager

abstract class CacheUpdateResource<ApiData, CacheData, AppData>(
    private val networkStateManager: NetworkStateManager
) : NetworkBoundResource<ApiData, CacheData, AppData>() {

    override fun execute() {
        super.execute()
        if (networkStateManager.isOnline()) {
            doNetworkRequest()
        } else {
            onErrorReturn(
                ApiErrorResponse(
                    statusCode = StatusCode.INTERNAL_ERROR
                )
            )
        }
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ApiData>) {
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(DataState.Success(getAppDataSuccessResponse(dataSuccessResponse)))
    }
}