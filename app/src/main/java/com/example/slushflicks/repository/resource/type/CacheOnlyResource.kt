package com.example.slushflicks.repository.resource.type

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiSuccessResponse
import com.example.slushflicks.repository.resource.NetworkBoundResource
import com.example.slushflicks.ui.state.DataErrorResponse
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.utils.livedata.AbsentLiveData
import kotlinx.coroutines.Job

abstract class CacheOnlyResource<ApiData, CacheData, AppData> : NetworkBoundResource<ApiData, CacheData, AppData>() {

    override fun execute() {
        super.execute()
        doCacheRequest()
    }

    override suspend fun createCacheRequestAndReturn() {
        val cacheResponse = getFromCache()
        cacheResponse?.let { data ->
            onCompleteJob(DataState.Success<AppData>(getAppDataSuccessResponse(data)))
        } ?: onCompleteJob(DataState.Error<AppData>(DataErrorResponse()))
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ApiData>) {}

    override fun createCall(): LiveData<ApiResponse<ApiData>> {
        return AbsentLiveData.create()
    }

    override suspend fun updateLocalDb(cacheData: CacheData?) {}

    override fun setJob(job: Job) {

    }
}