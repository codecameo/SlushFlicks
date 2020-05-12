package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.api.home.tv.model.TvShowDetailsApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.resource.type.CacheFirstNetworkUpdateResource
import com.sifat.slushflicks.ui.helper.getTvDetails
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getDistinct
import kotlinx.coroutines.Job

class TvDetailsNetworkResource(
    private val dataManager: DataManager,
    private val tvService: TvService,
    private val request: RequestModel,
    private val jobManager: JobManager,
    networkStateManager: NetworkStateManager
) : CacheFirstNetworkUpdateResource<TvShowDetailsApiModel, TvModel, TvModel>(
    networkStateManager
) {

    /**
     * In case App is representing a different model than the database model, the conversion can take place here,
     * In this case app and database is using the same [MovieModel], thus conversion is not needed
     * */
    override fun getAppDataSuccessResponse(response: DataSuccessResponse<TvModel>): DataSuccessResponse<TvModel> {
        return response
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<TvShowDetailsApiModel>): DataSuccessResponse<TvModel> {
        return DataSuccessResponse(
            data = getTvDetails(response.data),
            message = response.message
        )
    }

    override fun createCall(): LiveData<ApiResponse<TvShowDetailsApiModel>> {
        return tvService.getTvShowDetails(
            apiKey = request.apiKey,
            tvShowId = request.tvShowId
        )
    }

    override suspend fun updateLocalDb(cacheData: TvModel?) {
        cacheData?.let { model ->
            dataManager.updateTvDetails(model)
        }
    }

    override fun setJob(job: Job) {
        jobManager.addJob(TAG, job)
    }

    data class RequestModel(val apiKey: String, val tvShowId: Long)

    override fun loadFromCache(): LiveData<TvModel> {
        return dataManager.getTvShowDetails(request.tvShowId).getDistinct()
    }

    companion object {
        private const val TAG = "TvDetailsNetworkResourc"
    }
}