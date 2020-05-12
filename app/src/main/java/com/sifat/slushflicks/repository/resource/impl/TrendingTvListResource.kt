package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.utils.api.NetworkStateManager

class TrendingTvListResource(
    tvService: TvService,
    requestModel: RequestModel,
    dataManager: DataManager,
    collection: String,
    networkStateManager: NetworkStateManager,
    jobManager: JobManager
) : TvListNetworkResource(
    tvService = tvService,
    requestModel = requestModel,
    collection = collection,
    dataManager = dataManager,
    networkStateManager = networkStateManager,
    jobManager = jobManager
) {

    override fun createCall(): LiveData<ApiResponse<TvListApiModel>> {
        return tvService.getTrendingTvShow(
            apiKey = requestModel.apiKey,
            tag = requestModel.apiTag,
            page = requestModel.page
        )
    }
}