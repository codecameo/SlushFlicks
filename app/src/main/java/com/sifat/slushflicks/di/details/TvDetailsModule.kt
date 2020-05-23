package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.BuildConfig
import com.sifat.slushflicks.api.home.tv.TvService
import com.sifat.slushflicks.di.constant.NAME_DYNAMIC_BASE_LINK
import com.sifat.slushflicks.di.constant.NAME_DYNAMIC_DOMAIN
import com.sifat.slushflicks.helper.DynamicLinkProvider
import com.sifat.slushflicks.helper.JobManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
object TvDetailsModule {

    @JvmStatic
    @TvDetailsScope
    @Provides
    fun provideTvService(retrofit: Retrofit): TvService =
        retrofit.create(TvService::class.java)

    @JvmStatic
    @TvDetailsScope
    @Provides
    @Named(NAME_DYNAMIC_BASE_LINK)
    fun getDeepLinkBaseUrl(): String {
        return BuildConfig.DYNAMIC_LINK_BASE_URL
    }

    @JvmStatic
    @TvDetailsScope
    @Provides
    @Named(NAME_DYNAMIC_DOMAIN)
    fun getDeepLinkDomain(): String {
        return BuildConfig.DYNAMIC_LINK_DOMAIN
    }

    @JvmStatic
    @TvDetailsScope
    @Provides
    fun getDeepLinkProvider(
        @Named(NAME_DYNAMIC_BASE_LINK) baseUrl: String, @Named(
            NAME_DYNAMIC_DOMAIN
        ) domain: String
    ): DynamicLinkProvider {
        return DynamicLinkProvider(baseUrl, domain)
    }

    // Provide new instance of job manager when needed
    @JvmStatic
    @Provides
    fun provideJobManager() = JobManager()
}