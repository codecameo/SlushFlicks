package com.example.slushflicks.di.app

import android.content.Context
import com.example.slushflicks.BuildConfig
import com.example.slushflicks.BuildConfig.API_KEY
import com.example.slushflicks.SlushFlicksApplication
import com.example.slushflicks.di.constant.NAME_API_KEY
import com.example.slushflicks.utils.api.NetworkStateManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.CallAdapter.Factory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class AppModule {

    @AppScope
    @Provides
    fun getGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @AppScope
    @Provides
    fun getApiClient(gson: Gson, factory: Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL.plus(BuildConfig.API_VERSION))
            .addCallAdapterFactory(factory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @AppScope
    @Provides
    @Named(NAME_API_KEY)
    fun getApiKey(): String {
        return API_KEY
    }

    @AppScope
    @Provides
    fun getContext(slushFlicksApplication: SlushFlicksApplication): Context {
        return slushFlicksApplication
    }

    @AppScope
    @Provides
    fun getNetworkConnectivityManager(context: Context): NetworkStateManager {
        return NetworkStateManager(context)
    }
}