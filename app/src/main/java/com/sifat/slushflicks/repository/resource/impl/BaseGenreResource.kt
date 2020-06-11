package com.sifat.slushflicks.repository.resource.impl

import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.home.genre.model.GenreListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.repository.resource.type.CacheUpdateResource
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job

abstract class BaseGenreResource(
    private val dataManager: DataManager,
    networkStateManager: NetworkStateManager,
    dispatcher: CoroutineDispatcher
) : CacheUpdateResource<GenreListApiModel, List<GenreModel>, List<GenreModel>>(
    networkStateManager,
    dispatcher
) {
    override suspend fun updateLocalDb(cacheData: List<GenreModel>?) {
        cacheData?.run {
            dataManager.saveGenre(this)
        }
    }

    override fun setJob(job: Job) {}

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<List<GenreModel>>) =
        response

    override fun getDataSuccessResponse(response: ApiSuccessResponse<GenreListApiModel>): DataSuccessResponse<List<GenreModel>> {
        return DataSuccessResponse(data = response.data?.genres)
    }
}