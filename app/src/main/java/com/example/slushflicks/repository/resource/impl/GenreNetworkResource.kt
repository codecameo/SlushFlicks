package com.example.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiSuccessResponse
import com.example.slushflicks.api.home.genre.GenreService
import com.example.slushflicks.api.home.genre.model.GenreListApiModel
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.repository.resource.type.CacheOnlyResource
import com.example.slushflicks.repository.resource.type.NetworkOnlyResource
import com.example.slushflicks.ui.state.DataSuccessResponse
import com.example.slushflicks.utils.api.NetworkStateManager
import com.example.slushflicks.utils.livedata.AbsentLiveData
import kotlinx.coroutines.Job

class GenreNetworkResource(
    private val dataManager: DataManager
) : CacheOnlyResource<GenreListApiModel, List<GenreModel>, List<GenreModel>>() {

    override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<GenreListApiModel>) {
        updateLocalDb(response.data?.genres)
    }

    override fun loadFromCache(): LiveData<List<GenreModel>> {
        //Empty Implementation
        return AbsentLiveData.create()
    }

    override suspend fun getFromCache(): List<GenreModel>? {
        return dataManager.loadGenres()
    }

    /*override suspend fun updateLocalDb(cacheData: List<GenreModel>?) {
        cacheData?.let {
            dataManager.saveGenre(cacheData)
        }
    }*/

    override fun setJob(job: Job) {

    }

    override fun getAppDataFromCache(response: List<GenreModel>): DataSuccessResponse<List<GenreModel>> {
        return DataSuccessResponse(response)
    }
}