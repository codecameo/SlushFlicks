package com.sifat.slushflicks.repository.resource.type

import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.repository.resource.Resource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher

abstract class CacheUpdateResource<ApiData, CacheData, AppData>(
    private val networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher
) : Resource<ApiData, CacheData, AppData>(dispatcher) {

    override fun execute() {
        super.execute()
        if (networkStateManager.isOnline()) {
            doNetworkRequest()
        } else {
            onErrorReturn(getInternalErrorResponse())
        }
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ApiData>) {
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(DataState.Success(getAppDataSuccessResponse(dataSuccessResponse)))
    }
}