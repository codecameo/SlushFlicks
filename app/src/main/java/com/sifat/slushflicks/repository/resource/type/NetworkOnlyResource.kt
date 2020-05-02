package com.sifat.slushflicks.repository.resource.type

import com.sifat.slushflicks.api.ApiErrorResponse
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.repository.resource.NetworkBoundResource
import com.sifat.slushflicks.utils.api.NetworkStateManager

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
}