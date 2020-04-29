package com.example.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.example.slushflicks.api.ApiResponse
import com.example.slushflicks.api.ApiSuccessResponse
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.api.home.movie.model.MovieListApiModel
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.model.MovieModel
import com.example.slushflicks.model.MovieModelMinimal
import com.example.slushflicks.repository.resource.type.NetworkFirstSilentUpdateResource
import com.example.slushflicks.ui.helper.getCollectionModels
import com.example.slushflicks.ui.helper.getMetaData
import com.example.slushflicks.ui.helper.getMovieList
import com.example.slushflicks.ui.helper.getMovieMinimalModel
import com.example.slushflicks.ui.state.DataSuccessResponse
import com.example.slushflicks.utils.api.NetworkStateManager
import com.example.slushflicks.utils.livedata.AbsentLiveData
import kotlinx.coroutines.Job

open class MovieListNetworkResource(
    protected val movieService: MovieService,
    protected val requestModel: RequestModel,
    protected val dataManager: DataManager,
    private val collection: String,
    networkStateManager: NetworkStateManager
) : NetworkFirstSilentUpdateResource<MovieListApiModel, List<MovieModel>, List<MovieModelMinimal>>(
    networkStateManager
) {

    override fun createCall(): LiveData<ApiResponse<MovieListApiModel>> {
        return movieService.getMoviesList(
            apiKey = requestModel.apiKey,
            page = requestModel.page,
            tag = requestModel.apiTag,
            collection = collection
        )
    }

    override fun loadFromCache(): LiveData<List<MovieModel>> {
        //TODO access db when on error loading from network
        return AbsentLiveData.create()
    }

    override suspend fun updateLocalDb(cacheData: List<MovieModel>?) {
        if (!cacheData.isNullOrEmpty()) {
            // Insert with Ignore strategy
            dataManager.softInsertMovie(cacheData)
            val collectionModels = getCollectionModels(cacheData, collection)
            if (requestModel.page == 1) {
                dataManager.insertNewMovieCollection(collection, collectionModels)
            } else {
                dataManager.addMovieCollection(collectionModels)
            }
        }
    }

    override suspend fun getFromCache(): List<MovieModel>? {
        return dataManager.getMovies(collection)
    }

    override fun setJob(job: Job) {

    }

    data class RequestModel(val page: Int, val apiKey: String, val apiTag: String)

    override fun getAppDataSuccessResponse(response: List<MovieModel>?): DataSuccessResponse<List<MovieModelMinimal>> {
        return DataSuccessResponse(getMovieMinimalModel(response))
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<MovieListApiModel>): DataSuccessResponse<List<MovieModel>> {
        return DataSuccessResponse(
            data = getMovieList(response.data?.results, dataManager.getGenres()),
            metaData = getMetaData(response.data),
            message = response.message
        )
    }

}