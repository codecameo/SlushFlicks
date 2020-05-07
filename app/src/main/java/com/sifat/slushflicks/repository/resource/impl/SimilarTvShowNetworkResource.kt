package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.resource.type.NetworkOnlyResource
import com.sifat.slushflicks.ui.helper.getMetaData
import com.sifat.slushflicks.ui.helper.getShowMinimalModel
import com.sifat.slushflicks.ui.helper.getTvList
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.Job

class SimilarTvShowNetworkResource(
    private val tvService: TvService,
    private val dataManager: DataManager,
    private val requestModel: RequestModel,
    networkStateManager: NetworkStateManager
) : NetworkOnlyResource<TvListApiModel, List<TvModel>, List<ShowModelMinimal>>(
    networkStateManager
) {
    override fun createCall(): LiveData<ApiResponse<TvListApiModel>> {
        return tvService.getRelatedTvShows(
            apiKey = requestModel.apiKey,
            relation = requestModel.relationType,
            tvShowId = requestModel.tvShowId,
            page = 1,
            tag = requestModel.apiTag
        )
    }

    override suspend fun updateLocalDb(cacheData: List<TvModel>?) {
        /** Skip caching, since it's taking too much memory in the device*/
        /*if (!cacheData.isNullOrEmpty()) {
            // Insert with Ignore strategy
            dataManager.softInsertMovie(cacheData)
        }*/
    }

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<TvListApiModel>) {
        /**
         * Change Api response to data response which viewModel will use to
         * show relevant information in view
         * */
        val dataSuccessResponse = getDataSuccessResponse(response)
        updateLocalDb(dataSuccessResponse.data)
        onCompleteJob(
            DataState.Success<List<ShowModelMinimal>>(
                getAppDataSuccessResponse(dataSuccessResponse)
            )
        )
    }

    override fun setJob(job: Job) {

    }

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<List<TvModel>>): DataSuccessResponse<List<ShowModelMinimal>> {
        return DataSuccessResponse(
            data = getShowMinimalModel(response.data),
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

    data class RequestModel(
        val apiKey: String,
        val apiTag: String,
        val tvShowId: Long,
        val relationType: String
    )
}