package com.sifat.slushflicks.di.home.tvshow

import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.di.home.HomeScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class TvModule {
    @HomeScope
    @Provides
    fun provideTvService(retrofit: Retrofit): TvService =
        retrofit.create(TvService::class.java)
}