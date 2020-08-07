package com.sifat.slushflicks.repository.resource.type

import androidx.lifecycle.Observer
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.repository.resource.Resource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher

abstract class CacheFirstNetworkUpdateResource<ApiData, CacheData, AppData>(
    private val networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher
) : Resource<ApiData, CacheData, AppData>(dispatcher) {

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
        job.complete()
        /**
         * In case of success response Database will the updated response back to view,
         * In case of error, notify the user about it
         * */
        if (dataState is DataState.Error) {
            postValue(dataState)
        }
    }

    override fun doCacheRequest() {
        result.addSource(loadFromCache(), Observer { cacheData ->
            result.value = DataState.Success<AppData>(
                getAppDataSuccessResponse(
                    DataSuccessResponse(cacheData)
                )
            )
        })
    }
}