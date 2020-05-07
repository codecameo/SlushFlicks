package com.sifat.slushflicks.repository.tv.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.repository.resource.impl.TvListNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.Label
import com.sifat.slushflicks.utils.api.NetworkStateManager
import javax.inject.Inject
import javax.inject.Named

class AirTodayTvRepositoryImpl @Inject constructor(
    tvService: TvService,
    @Named(NAME_API_KEY) apiKey: String,
    dataManager: DataManager,
    networkStateManager: NetworkStateManager
) : BaseTvListRepositoryImpl(tvService, apiKey, dataManager, networkStateManager) {
    override val collection: String
        get() = Label.AIRING_TODAY

    override fun getTvList(nextPage: Int): LiveData<DataState<Int>> {
        val requestModel = TvListNetworkResource.RequestModel(
            page = nextPage,
            apiKey = apiKey,
            apiTag = ApiTag.AIRING_TODAY_TV_API_TAG
        )
        val tvListNetworkResource = TvListNetworkResource(
            requestModel = requestModel,
            tvService = tvService,
            networkStateManager = networkStateManager,
            dataManager = dataManager,
            collection = collection
        )
        return tvListNetworkResource.asLiveData()
    }
}