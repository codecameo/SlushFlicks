package com.sifat.slushflicks.repository.tv.impl

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.TvListNetworkResource
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.Label
import com.sifat.slushflicks.utils.api.NetworkStateManager
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Named

class PopularTvRepositoryImpl @Inject constructor(
    private val tvService: TvService,
    @Named(NAME_API_KEY)
    private val apiKey: String,
    private val networkStateManager: NetworkStateManager,
    private val dispatcher: CoroutineDispatcher,
    jobManager: JobManager,
    dataManager: DataManager
) : BaseTvListRepositoryImpl(dataManager, jobManager) {
    override val collection: String
        get() = Label.POPULAR_LABEL

    override fun getTvList(nextPage: Int): LiveData<DataState<Int>> {
        val requestModel = TvListNetworkResource.RequestModel(
            page = nextPage,
            apiKey = apiKey,
            collection = collection,
            apiTag = ApiTag.POPULAR_TV_API_TAG
        )
        val tvListNetworkResource = TvListNetworkResource(
            requestModel = requestModel,
            tvService = tvService,
            networkStateManager = networkStateManager,
            dataManager = dataManager,
            jobManager = jobManager,
            dispatcher = dispatcher
        )
        return tvListNetworkResource.asLiveData()
    }
}