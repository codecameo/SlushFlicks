package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.repository.tv.TvDetailsRepository
import com.sifat.slushflicks.utils.api.NetworkStateManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class TvDetailsModule {

    @TvDetailsScope
    @Provides
    fun provideTvService(retrofit: Retrofit): TvService =
        retrofit.create(TvService::class.java)

    @TvDetailsScope
    @Provides
    fun provideDetailsRepository(
        tvService: TvService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): TvDetailsRepository {
        return TvDetailsRepository(
            tvService = tvService,
            dataManager = dataManager,
            apiKey = apiKey,
            networkStateManager = networkStateManager
        )
    }
}