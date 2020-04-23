package com.example.slushflicks.di.module

import com.example.slushflicks.BuildConfig
import com.example.slushflicks.api.ApiClient
import com.example.slushflicks.di.constant.NAME_API_KEY
import com.example.slushflicks.di.scope.AppScope
import com.example.slushflicks.models.MovieListModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {

    @AppScope
    @Provides
    fun getApiClient() : ApiClient {
        return ApiClient()
    }

    @AppScope
    @Provides
    @Named(NAME_API_KEY)
    fun getApiKey() : String {
        return BuildConfig.API_KEY
    }
}