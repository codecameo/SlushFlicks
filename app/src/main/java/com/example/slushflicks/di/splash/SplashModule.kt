package com.example.slushflicks.di.splash

import com.example.slushflicks.api.home.genre.GenreService
import com.example.slushflicks.api.home.movie.MovieService
import com.example.slushflicks.data.DataManager
import com.example.slushflicks.di.constant.NAME_API_KEY
import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.repository.GenreRepository
import com.example.slushflicks.utils.api.NetworkStateManager
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
