package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.BuildConfig
import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.di.constant.NAME_DYNAMIC_BASE_LINK
import com.sifat.slushflicks.di.constant.NAME_DYNAMIC_DOMAIN
import com.sifat.slushflicks.provider.DynamicLinkProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class MovieDetailsModule {

    @MovieDetailsScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @MovieDetailsScope
    @Provides
    @Named(NAME_DYNAMIC_BASE_LINK)
    fun getDeepLinkBaseUrl(): String {
        return BuildConfig.DYNAMIC_LINK_BASE_URL
    }

    @MovieDetailsScope
    @Provides
    @Named(NAME_DYNAMIC_DOMAIN)
    fun getDeepLinkDomain(): String {
        return BuildConfig.DYNAMIC_LINK_DOMAIN
    }

    @MovieDetailsScope
    @Provides
    fun getDeepLinkProvider(
        @Named(NAME_DYNAMIC_BASE_LINK) baseUrl: String, @Named(
            NAME_DYNAMIC_DOMAIN
        ) domain: String
    ): DynamicLinkProvider {
        return DynamicLinkProvider(baseUrl, domain)
    }
}