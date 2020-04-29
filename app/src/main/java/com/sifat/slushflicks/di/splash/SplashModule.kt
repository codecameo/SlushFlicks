package com.sifat.slushflicks.di.splash

import com.sifat.slushflicks.api.home.genre.GenreService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.repository.GenreRepository
import com.sifat.slushflicks.utils.api.NetworkStateManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class SplashModule {

    @SplashScope
    @Provides
    fun getMovieService(retrofit: Retrofit): GenreService =
        retrofit.create(GenreService::class.java)

    @SplashScope
    @Provides
    fun providesGenreRepository(
        genreService: GenreService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ) : GenreRepository {
        return GenreRepository(
            genreService = genreService,
            apiKey = apiKey,
            networkStateManager = networkStateManager,
            dataManager = dataManager
        )
    }
}
