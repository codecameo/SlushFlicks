package com.sifat.slushflicks.di.splash

import com.sifat.slushflicks.api.home.genre.GenreService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.repository.genre.GenreRepository
import com.sifat.slushflicks.repository.genre.impl.GenreRepositoryImpl
import com.sifat.slushflicks.repository.resource.impl.GenreNetworkResource
import com.sifat.slushflicks.repository.resource.impl.MovieGenreNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvGenreNetworkResource
import com.sifat.slushflicks.utils.api.NetworkStateManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module(includes = [InnerModule::class])
object SplashModule {

    @JvmStatic
    @SplashScope
    @Provides
    fun provideGenreService(retrofit: Retrofit): GenreService =
        retrofit.create(GenreService::class.java)

    @JvmStatic
    @SplashScope
    @Provides
    fun provideGenreResource(dataManager: DataManager) = GenreNetworkResource(dataManager)

    @JvmStatic
    @SplashScope
    @Provides
    fun provideTvGenreResource(
        genreService: GenreService,
        dataManager: DataManager,
        @Named(NAME_API_KEY)
        apiKey: String,
        networkStateManager: NetworkStateManager
    ) = TvGenreNetworkResource(
        genreService = genreService,
        dataManager = dataManager,
        apiKey = apiKey,
        networkStateManager = networkStateManager
    )

    @JvmStatic
    @SplashScope
    @Provides
    fun provideMovieGenreResource(
        genreService: GenreService,
        dataManager: DataManager,
        @Named(NAME_API_KEY)
        apiKey: String,
        networkStateManager: NetworkStateManager
    ) = MovieGenreNetworkResource(
        genreService = genreService,
        dataManager = dataManager,
        apiKey = apiKey,
        networkStateManager = networkStateManager
    )
}

@Module
abstract class InnerModule {
    @SplashScope
    @Binds
    abstract fun providesGenreRepository(genreRepositoryImpl: GenreRepositoryImpl): GenreRepository
}
