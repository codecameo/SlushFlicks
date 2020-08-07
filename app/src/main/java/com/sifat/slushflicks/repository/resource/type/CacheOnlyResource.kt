package com.sifat.slushflicks.repository.resource.type

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.repository.resource.Resource
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.livedata.AbsentLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job

abstract class CacheOnlyResource<ApiData, CacheData, AppData>(
    dispatcher: CoroutineDispatcher
) : Resource<ApiData, CacheData, AppData>(dispatcher) {

    override fun execute() {
        super.execute()
        doCacheRequest()
    }

    override suspend fun createCacheRequestAndReturn() {
        val cacheResponse = getFromCache()
        cacheResponse?.let { data ->
            onCompleteJob(
                DataState.Success<AppData>(
                    getAppDataSuccessResponse(
                        DataSuccessResponse(
                            data = data
                        )
                    )
                )
            )
        } ?: onCompleteJob(DataState.Error<AppData>(DataErrorResponse()))
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ApiData>) {}

    override fun createCall(): LiveData<ApiResponse<ApiData>> {
        return AbsentLiveData.create()
    }

    override suspend fun updateLocalDb(cacheData: CacheData?) {}

    override fun setJob(job: Job) {}
}