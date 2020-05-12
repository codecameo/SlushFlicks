package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.resource.type.NetworkOnlyResource
import com.sifat.slushflicks.ui.helper.getCollectionModels
import com.sifat.slushflicks.ui.helper.getMetaData
import com.sifat.slushflicks.ui.helper.getTvList
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.Job

open class TvListNetworkResource(
    protected val tvService: TvService,
    protected val requestModel: RequestModel,
    protected val dataManager: DataManager,
    private val collection: String,
    private val jobManager: JobManager,
    networkStateManager: NetworkStateManager
) : NetworkOnlyResource<TvListApiModel, List<TvModel>, Int>(
    networkStateManager
) {

    override fun createCall(): LiveData<ApiResponse<TvListApiModel>> {
        return tvService.getTvShowList(
            apiKey = requestModel.apiKey,
            page = requestModel.page,
            tag = requestModel.apiTag,
            collection = collection
        )
    }

    override suspend fun updateLocalDb(cacheData: List<TvModel>?) {
        if (!cacheData.isNullOrEmpty()) {
            // Insert with Ignore strategy
            dataManager.softInsertTv(cacheData)
            val collectionModels = getCollectionModels(cacheData, collection, requestModel.page)
            if (requestModel.page == 1) {
                dataManager.insertNewTvCollection(collection, collectionModels)
            } else {
                dataManager.addTvCollection(collectionModels)
            }
        }
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<TvListApiModel>) {
        /**
         * Change Api response to data response which viewModel will use to
         * show relevant information in view
         * */
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(
            DataState.Success<Int>(
                getAppDataSuccessResponse(dataSuccessResponse)
            )
        )
    }

    override fun setJob(job: Job) {
        jobManager.addJob(requestModel.apiTag, job)
    }

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<List<TvModel>>): DataSuccessResponse<Int> {
        return DataSuccessResponse(
            data = response.data?.size,
            metaData = response.metaData,
            message = response.message
        )
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<TvListApiModel>): DataSuccessResponse<List<TvModel>> {
        return DataSuccessResponse(
            data = getTvList(response.data?.results, dataManager.getGenres()),
            metaData = getMetaData(response.data),
            message = response.message
        )
    }

    data class RequestModel(val page: Int, val apiKey: String, val apiTag: String)
}