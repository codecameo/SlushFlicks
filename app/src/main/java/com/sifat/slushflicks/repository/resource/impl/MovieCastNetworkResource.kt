package com.sifat.slushflicks.repository.resource.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.repository.resource.type.CacheUpdateResource
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getCastListImageUrl
import kotlinx.coroutines.Job

class MovieCastNetworkResource(
    private val movieService: MovieService,
    private val dataManager: DataManager,
    private val requestModel: RequestModel,
    networkStateManager: NetworkStateManager
) : CacheUpdateResource<CreditsApiModel, List<CastModel>, Int>(networkStateManager) {
    override fun createCall(): LiveData<ApiResponse<CreditsApiModel>> {
        return movieService.getMovieCredits(
            movieId = requestModel.movieId,
            apiKey = requestModel.apiKey
        )
    }

    override suspend fun updateLocalDb(cacheData: List<CastModel>?) {
        cacheData?.let {
            // Saving only first 10 casts
            val size = if (cacheData.size > 10) 10 else cacheData.size
            val castList = List<CastModel>(size) { index ->
                val castModel = CastModel(
                    castId = cacheData[index].castId,
                    profileImage = getCastListImageUrl(cacheData[index].profileImage),
                    order = cacheData[index].order,
                    name = cacheData[index].name,
                    character = cacheData[index].character
                )
                castModel
            }
            dataManager.updateMovieDetails(castList, requestModel.movieId)
        }
    }

    override fun setJob(job: Job) {

    }

    override fun getAppDataSuccessResponse(response: DataSuccessResponse<List<CastModel>>): DataSuccessResponse<Int> {
        return DataSuccessResponse(
            data = response.data?.size,
            message = response.message
        )
    }

    override fun getDataSuccessResponse(response: ApiSuccessResponse<CreditsApiModel>): DataSuccessResponse<List<CastModel>> {
        return DataSuccessResponse(
            data = response.data?.casts,
            message = response.message
        )
    }


    data class RequestModel(val apiKey: String, val movieId: Long)
}