package com.sifat.slushflicks.repository.tv

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.repository.resource.impl.TvListNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.Label
import com.sifat.slushflicks.utils.api.NetworkStateManager

class TopRatedTvRepository(
    tvService: TvService,
    apiKey: String,
    dataManager: DataManager,
    networkStateManager: NetworkStateManager
) : BaseTvListRepository(tvService, apiKey, dataManager, networkStateManager) {
    override val collection: String
        get() = Label.TOP_RATED_LABEL

    override fun getTvList(nextPage: Int): LiveData<DataState<Int>> {
        val requestModel = TvListNetworkResource.RequestModel(
            page = nextPage,
            apiKey = apiKey,
            apiTag = ApiTag.TOP_RATED_TV_API_TAG
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