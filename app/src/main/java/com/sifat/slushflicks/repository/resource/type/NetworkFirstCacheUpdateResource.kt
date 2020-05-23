package com.sifat.slushflicks.repository.resource.type

import com.sifat.slushflicks.api.ApiErrorResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.repository.resource.Resource
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher

abstract class NetworkFirstCacheUpdateResource<ApiData, CacheData, AppData>(
    private val networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher
) : Resource<ApiData, CacheData, AppData>(dispatcher) {

    override fun execute() {
        super.execute()
        if (networkStateManager.isOnline()) {
            doNetworkRequest()
        } else {
            doCacheRequest()
        }
    }

    override fun onErrorReturn(apiErrorResponse: ApiErrorResponse<ApiData>) {
        doCacheRequest()
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ApiData>) {
        /**
         * Change Api response to data response which viewModel will use to
         * show relevant information in view
         * */
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(
            DataState.Success<AppData>(getAppDataSuccessResponse(dataSuccessResponse))
        )
    }

    override suspend fun createCacheRequestAndReturn() {
        val cacheResponse = getFromCache()
        cacheResponse?.let { data ->
            onCompleteJob(
                DataState.Success<AppData>(
                    getAppDataSuccessResponse(
                        DataSuccessResponse(
                            data
                        )
                    )
                )
            )
        } ?: onCompleteJob(DataState.Error<AppData>(DataErrorResponse()))
    }
}