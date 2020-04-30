package com.sifat.slushflicks.repository.resource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.sifat.slushflicks.api.*
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import kotlinx.coroutines.*

abstract class NetworkBoundResource<ApiData, CacheData, AppData> {

    private val TAG: String = "AppDebug"

    protected val result = MediatorLiveData<DataState<AppData>>()
    protected lateinit var job: CompletableJob
    protected lateinit var coroutineScope: CoroutineScope

    fun doCacheRequest() {
        coroutineScope.launch {
            // View data from cache only and return
            createCacheRequestAndReturn()
        }
    }

    fun doNetworkRequest() {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                // make network call
                val apiResponse = createCall()
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    coroutineScope.launch {
                        handleNetworkCall(response)
                    }
                }
            }
        }
    }

    private suspend fun handleNetworkCall(response: ApiResponse<ApiData>) {
        when (response) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse -> {
                onErrorReturn(response)
            }
            is ApiEmptyResponse -> {
                onErrorReturn(
                    ApiErrorResponse(
                        statusCode = response.statusCode,
                        apiTag = response.apiTag
                    )
                )
            }
        }
    }

    fun onCompleteJob(dataState: DataState<AppData>) {
        GlobalScope.launch(Dispatchers.Main) {
            job.complete()
            setValue(dataState)
        }
    }

    protected open fun onErrorReturn(apiErrorResponse: ApiErrorResponse<ApiData>) {
        val dataErrorResponse = DataErrorResponse<AppData>(
            statusCode = apiErrorResponse.statusCode,
            apiTag = apiErrorResponse.apiTag,
            errorMessage = apiErrorResponse.errorMessage
        )
        onCompleteJob(DataState.Error(dataErrorResponse))
    }

    fun onJobCancelled(errorResponse: DataErrorResponse<AppData>) {
        onCompleteJob(DataState.Error(errorResponse))
    }

    fun setValue(dataState: DataState<AppData>) {
        result.value = dataState
    }

    @UseExperimental(InternalCoroutinesApi::class)
    private fun initNewJob(): Job {
        Log.d(TAG, "initNewJob: called.")
        job = Job() // create new job
        job.invokeOnCompletion(
            onCancelling = true,
            invokeImmediately = true,
            handler = object : CompletionHandler {
                override fun invoke(cause: Throwable?) {
                    if (job.isCancelled) {
                        Log.e(TAG, "NetworkBoundResource: Job has been cancelled.")
                        val dataErrorResponse = DataErrorResponse<AppData>(
                            statusCode = StatusCode.REQUEST_CANCELLED,
                            errorMessage = cause?.message
                        )
                        onJobCancelled(dataErrorResponse)
                    } else if (job.isCompleted) {
                        Log.e(TAG, "NetworkBoundResource: Job has been completed.")
                    }
                }
            })
        coroutineScope = CoroutineScope(Dispatchers.IO + job)
        return job
    }

    fun asLiveData() : LiveData<DataState<AppData>> {
        execute()
        return result
    }

    protected open suspend fun createCacheRequestAndReturn() {}

    abstract suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<ApiData>)

    abstract fun createCall(): LiveData<ApiResponse<ApiData>>

    @Deprecated("Use getFromCache instead")
    abstract fun loadFromCache(): LiveData<CacheData>

    protected open suspend fun getFromCache(): CacheData? {
        return null
    }

    abstract suspend fun updateLocalDb(cacheData: CacheData?)

    abstract fun setJob(job: Job)

    protected open fun execute() {
        setJob(initNewJob())
    }

    /**
     * In case of local cache wrap it with [DataResponse]
     * */
    abstract fun getAppDataSuccessResponse(response: DataSuccessResponse<CacheData>): DataSuccessResponse<AppData>

    // ApiResponse is needed in case there is message from the api
    abstract fun getDataSuccessResponse(response: ApiSuccessResponse<ApiData>): DataSuccessResponse<CacheData>
}