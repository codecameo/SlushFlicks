package com.sifat.slushflicks.repository.resource.type

import androidx.lifecycle.Observer
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.repository.resource.NetworkBoundResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager

abstract class CacheFirstNetworkUpdateResource<ApiData, CacheData, AppData>(
    private val networkStateManager: NetworkStateManager
) : NetworkBoundResource<ApiData, CacheData, AppData>() {

    override fun execute() {
        super.execute()
        doCacheRequest()
        if (networkStateManager.isOnline()) {
            doNetworkRequest()
        }
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ApiData>) {
        /**
         * Change Api response to data response
         * */
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(DataState.Success(getAppDataSuccessResponse(dataSuccessResponse)))
    }

    override fun onCompleteJob(dataState: DataState<AppData>) {
        // Database will the updated response back to view, so just completing the job
        job.complete()
    }

    override fun doCacheRequest() {
        result.addSource(listenToCache(), Observer { cacheData ->
            result.value = DataState.Success<AppData>(
                getAppDataSuccessResponse(
                    DataSuccessResponse(cacheData)
                )
            )
        })
    }
}