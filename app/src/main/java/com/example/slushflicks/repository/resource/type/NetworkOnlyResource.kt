package com.example.slushflicks.repository.resource.type

import com.example.slushflicks.api.ApiErrorResponse
import com.example.slushflicks.api.StatusCode
import com.example.slushflicks.repository.resource.NetworkBoundResource
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This network resource will only check internet to get the data
 * wont check database for cached data
 * */
abstract class NetworkOnlyResource<ApiData, CacheData, AppData> (private val networkStateManager: NetworkStateManager) :
    NetworkBoundResource<ApiData, CacheData, AppData>() {

    override fun execute() {
        if (networkStateManager.isOnline()) {
            doNetworkRequest()
        } else {
            onErrorReturn(
                ApiErrorResponse(
                    statusCode = StatusCode.INTERNAL_ERROR
                ))
        }
    }

    override suspend fun createCacheRequestAndReturn() {
        withContext(Dispatchers.Main) {
            val cacheResponse = loadFromCache()
            result.addSource(cacheResponse) { response ->
                result.removeSource(cacheResponse)
                onCompleteJob(DataState.Success<AppData>(getAppDataFromCache(response)))
            }
        }
    }
}