package com.sifat.slushflicks.repository.resource.type

import com.sifat.slushflicks.repository.resource.Resource
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher

/**
 * This network resource will only check internet to get the data
 * wont check database for cached data
 * */
abstract class NetworkOnlyResource<ApiData, CacheData, AppData>(
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
}