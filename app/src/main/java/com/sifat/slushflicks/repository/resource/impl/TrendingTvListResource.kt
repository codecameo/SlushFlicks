package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TrendingTvListResource(
    tvService: TvService,
    requestModel: RequestModel,
    dataManager: DataManager,
    networkStateManager: NetworkStateManager,
    jobManager: JobManager,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : TvListNetworkResource(
    tvService = tvService,
    requestModel = requestModel,
    dataManager = dataManager,
    networkStateManager = networkStateManager,
    jobManager = jobManager,
    dispatcher = dispatcher
) {
    override fun createCall(): LiveData<ApiResponse<TvListApiModel>> {
        return tvService.getTrendingTvShow(
            apiKey = requestModel.apiKey,
            tag = requestModel.apiTag,
            page = requestModel.page
        )
    }
}