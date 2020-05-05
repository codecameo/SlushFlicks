package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.di.constant.NAME_API_KEY
import com.sifat.slushflicks.repository.movie.MovieDetailsRepository
import com.sifat.slushflicks.utils.api.NetworkStateManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class DetailsModule {

    @DetailsScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @DetailsScope
    @Provides
    fun provideDetailsRepository(
        movieService: MovieService,
        @Named(NAME_API_KEY) apiKey: String,
        networkStateManager: NetworkStateManager,
        dataManager: DataManager
    ): MovieDetailsRepository {
        return MovieDetailsRepository(
            movieService = movieService,
            dataManager = dataManager,
            apiKey = apiKey,
            networkStateManager = networkStateManager
        )
    }
}