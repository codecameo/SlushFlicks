package com.example.slushflicks.di.app


import com.example.slushflicks.utils.api.LiveDataCallAdapterFactory
import dagger.Binds
import dagger.Module
import retrofit2.CallAdapter.Factory

@Module
abstract class AppBindModule {

    /**
     * Since it's faster than @Provides annotation
     * */
    @Binds
    abstract fun getCallAdapterFactory(liveDataCallAdapterFactory: LiveDataCallAdapterFactory) : Factory
}