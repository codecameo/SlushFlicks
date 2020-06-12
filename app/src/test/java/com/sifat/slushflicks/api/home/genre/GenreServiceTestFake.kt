package com.sifat.slushflicks.api.home.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiSuccessResponse
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.api.home.genre.model.GenreListApiModel
import com.sifat.slushflicks.utils.api.getNoResResponse
import com.sifat.slushflicks.utils.api.getUnAuthResponse
import com.sifat.slushflicks.utils.getGenreList

class GenreServiceTestFake(private val gson: Gson) : GenreService {
    var errorCode: Int? = null
    override fun getTvGenre(apiKey: String, tag: String): LiveData<ApiResponse<GenreListApiModel>> {
        val live = MutableLiveData<ApiResponse<GenreListApiModel>>()
        live.value = when (errorCode) {
            StatusCode.RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            StatusCode.UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> ApiSuccessResponse(GenreListApiModel(getGenreList()))
        }
        return live
    }

    override fun getMovieGenre(
        apiKey: String,
        tag: String
    ): LiveData<ApiResponse<GenreListApiModel>> {
        val live = MutableLiveData<ApiResponse<GenreListApiModel>>()
        live.value = when (errorCode) {
            StatusCode.RESOURCE_NOT_FOUND -> getNoResResponse(gson, tag)
            StatusCode.UNAUTHORIZED -> getUnAuthResponse(gson, tag)
            else -> ApiSuccessResponse(GenreListApiModel(getGenreList()))
        }
        return live
    }
}