package com.example.slushflicks.repository.resource.type

import com.example.slushflicks.api.ApiErrorResponse
import com.example.slushflicks.repository.resource.NetworkBoundResource
import com.example.slushflicks.ui.state.DataErrorResponse
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


abstract class NetworkFirstSilentUpdateResource<ApiData, CacheData, AppData>(private val networkStateManager: NetworkStateManager) :
    NetworkBoundResource<ApiData, CacheData, AppData>() {

    override fun execute() {
        if (networkStateManager.isOnline()) {
            doNetworkRequest()
        } else {
            doCacheRequest()
        }
    }

    override fun onErrorReturn(apiErrorResponse: ApiErrorResponse<ApiData>) {
        doCacheRequest()
    }

    override suspend fun createCacheRequestAndReturn() {
        val cacheResponse = getFromCache()
        cacheResponse?.let { data ->
            onCompleteJob(DataState.Success<AppData>(getAppDataFromCache(data)))
        } ?: onCompleteJob(DataState.Error<AppData>(DataErrorResponse()))

        /*result.addSource(cacheResponse) { response ->
            result.removeSource(cacheResponse)

        }*/
    }
}