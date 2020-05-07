package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.repository.tv.TvDetailsRepository
import com.sifat.slushflicks.repository.tv.impl.TvDetailsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class TvDetailsBindModule {

    @TvDetailsScope
    @Binds
    abstract fun provideDetailsRepository(tvDetailsRepositoryImpl: TvDetailsRepositoryImpl): TvDetailsRepository
}