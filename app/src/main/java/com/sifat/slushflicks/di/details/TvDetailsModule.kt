package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.api.home.tv.TvService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class TvDetailsModule {

    @TvDetailsScope
    @Provides
    fun provideTvService(retrofit: Retrofit): TvService =
        retrofit.create(TvService::class.java)
}