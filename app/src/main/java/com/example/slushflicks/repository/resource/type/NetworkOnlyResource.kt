package com.example.slushflicks.repository.resource.type

import com.example.slushflicks.api.ApiErrorResponse
import com.example.slushflicks.api.StatusCode
import com.example.slushflicks.repository.resource.NetworkBoundResource
import com.example.slushflicks.utils.api.NetworkStateManager

/**
 * This network resource will only check internet to get the data
 * wont check database for cached data
 * */
abstract class NetworkOnlyResource<ApiData, CacheData, AppData> (private val networkStateManager: NetworkStateManager) :
    NetworkBoundResource<ApiData, CacheData, AppData>() {

    override fun execute() {
        super.execute()
        if (networkStateManager.isOnline()) {
            doNetworkRequest()
        } else {
            onErrorReturn(
                ApiErrorResponse(
                    statusCode = StatusCode.INTERNAL_ERROR
                ))
        }
    }

    /*override suspend fun createCacheRequestAndReturn() {
        val cacheResponse = getFromCache()
        cacheResponse?.let { data ->
            onCompleteJob(DataState.Success<AppData>(getAppDataFromCache(data)))
        } ?: onCompleteJob(DataState.Error<AppData>(DataErrorResponse()))

        *//*withContext(Dispatchers.Main) {
            val cacheResponse = loadFromCache()
            result.addSource(cacheResponse) { response ->
                result.removeSource(cacheResponse)
                onCompleteJob(DataState.Success<AppData>(getAppDataFromCache(response)))
            }
        }*//*
    }*/
}