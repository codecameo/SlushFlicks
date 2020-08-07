package com.sifat.slushflicks.repository.resource.impl

import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.home.genre.model.GenreListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.repository.resource.type.CacheOnlyResource
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class GenreNetworkResource(
    private val dataManager: DataManager,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CacheOnlyResource<GenreListApiModel, List<GenreModel>, List<GenreModel>>(dispatcher) {

    override suspend fun getFromCache() = dataManager.loadGenres()

    override fun setJob(job: Job) {}

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<List<GenreModel>>): DataSuccessResponse<List<GenreModel>> {
        return response
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<GenreListApiModel>): DataSuccessResponse<List<GenreModel>> {
        return DataSuccessResponse(
            data = response.data?.genres,
            message = response.message
        )
    }
}