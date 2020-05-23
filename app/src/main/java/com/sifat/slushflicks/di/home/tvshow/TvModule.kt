package com.sifat.slushflicks.di.home.tvshow

import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.tv.TvHomeRepository
import com.sifat.slushflicks.repository.tv.impl.TvHomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [InnerModule::class])
abstract class TvModule {

    @TvShowScope
    @Binds
    abstract fun provideTvHomeRepository(tvHomeRepositoryImpl: TvHomeRepositoryImpl): TvHomeRepository
}

@Module
object InnerModule {
    @JvmStatic
    @TvShowScope
    @Provides
    fun provideTvService(retrofit: Retrofit): TvService =
        retrofit.create(TvService::class.java)

    // Provide new instance of job manager when needed
    @JvmStatic
    @Provides
    fun provideJobManager() = JobManager()
}